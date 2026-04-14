package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lib.expression.*;

class ExpressionToJsLikeSyntaxTest {
    private static <E> String renderJsLike(TestSupport<E> testSupport, E expression) {
        return testSupport.v.jsLikeSyntaxPrinter().apply(expression);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void jsLikeSyntaxUsesPriorityToControlParentheses(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        assertEquals(
            "1 + 2 * 3",
            renderJsLike(testSupport, factory.addition(factory.literal("1"), factory.multiplication(factory.literal("2"), factory.literal("3"))))
        );
        assertEquals(
            "(1 + 2) * 3",
            renderJsLike(testSupport, factory.multiplication(factory.addition(factory.literal("1"), factory.literal("2")), factory.literal("3")))
        );
        assertEquals(
            "10 - (3 - 1)",
            renderJsLike(testSupport, factory.subtraction(factory.literal("10"), factory.subtraction(factory.literal("3"), factory.literal("1"))))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void jsLikeSyntaxPrintsOperatorsAndCalls(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        assertEquals(
            "x <= 10 ? f(1, y) : !ready || pow(2, 3)",
            renderJsLike(testSupport, factory.conditional(
                factory.lessThanOrEqual(factory.variableReference("x"), factory.literal("10")),
                factory.functionCall(factory.variableReference("f"), java.util.List.of(factory.literal("1"), factory.variableReference("y"))),
                factory.disjunction(factory.logicalNot(factory.variableReference("ready")), factory.exponentiation(factory.literal("2"), factory.literal("3")))
            ))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void jsLikeSyntaxUsesRightAssociativeBindingWhenNeeded(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        assertEquals(
            "a ? b : c ? d : e",
            renderJsLike(testSupport, factory.conditional(
                factory.variableReference("a"),
                factory.variableReference("b"),
                factory.conditional(
                    factory.variableReference("c"),
                    factory.variableReference("d"),
                    factory.variableReference("e")
                )
            ))
        );
    }
}