package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ExpressionMapperTraversalTest {
    private static <E> String render(TestSupport<E> testSupport, E expression) {
        return testSupport.v.jsLikeSyntaxPrinter().apply(expression);
    }

    private static <E> String typeName(TestSupport<E> testSupport, E expression) {
        return testSupport.v.expressionClassNameExtractor().apply(expression);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void mapperClonesEveryExpressionShape(String variant, TestSupport<E> testSupport) {
        var mapper = testSupport.v.expressionMapper((expression, produce) -> produce.get());

        for (var expression : testSupport.sampleNonVariableExpressions()) {
            assertEquals(render(testSupport, expression), render(testSupport, mapper.apply(expression)));
            assertEquals(typeName(testSupport, expression), typeName(testSupport, mapper.apply(expression)));
        }

        var traversal = testSupport.sampleTraversalExpression();
        var traversalClone = mapper.apply(traversal);
        assertEquals(render(testSupport, traversal), render(testSupport, traversalClone));
        assertEquals(typeName(testSupport, traversal), typeName(testSupport, traversalClone));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void constantFolderTraversesNestedExpressionArguments(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        var folder = testSupport.v.constantFolder();
        var expression = factory.functionCall(
            factory.variableReference("sum"),
            of(
                factory.addition(factory.literal("1"), factory.literal("2")),
                factory.conditional(factory.literal("1"), factory.literal("4"), factory.literal("5")),
                factory.disjunction(factory.literal("0"), factory.literal("1")),
                factory.negation(factory.literal("3"))
            )
        );

        assertEquals("sum(3, 4, 1, -3)", render(testSupport, folder.apply(expression)));
    }
}