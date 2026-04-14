package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HandlerTransformationHelpersTest {
    private static <E> String render(TestSupport<E> testSupport, E expression) {
        return testSupport.v.jsLikeSyntaxPrinter().apply(expression);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void constantFolderOnceHandlesFoldableOperatorsAndShortCircuit(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        var foldOnce = testSupport.v.constantFolderOnce();

        assertEquals("3", render(testSupport, foldOnce.apply(factory.addition(factory.literal("1"), factory.literal("2")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.subtraction(factory.literal("3"), factory.literal("2")))));
        assertEquals("6", render(testSupport, foldOnce.apply(factory.multiplication(factory.literal("3"), factory.literal("2")))));
        assertEquals("2", render(testSupport, foldOnce.apply(factory.division(factory.literal("6"), factory.literal("3")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.modulo(factory.literal("7"), factory.literal("3")))));
        assertEquals("8", render(testSupport, foldOnce.apply(factory.exponentiation(factory.literal("2"), factory.literal("3")))));
        assertEquals("-2", render(testSupport, foldOnce.apply(factory.negation(factory.literal("2")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.equality(factory.literal("2"), factory.literal("2")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.inequality(factory.literal("2"), factory.literal("3")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.lessThan(factory.literal("2"), factory.literal("3")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.greaterThan(factory.literal("3"), factory.literal("2")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.greaterThanOrEqual(factory.literal("3"), factory.literal("3")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.conjunction(factory.literal("0"), factory.variableReference("x")))));
        assertEquals("y", render(testSupport, foldOnce.apply(factory.conjunction(factory.literal("1"), factory.variableReference("y")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.disjunction(factory.literal("1"), factory.variableReference("x")))));
        assertEquals("y", render(testSupport, foldOnce.apply(factory.disjunction(factory.literal("0"), factory.variableReference("y")))));
        assertEquals("1", render(testSupport, foldOnce.apply(factory.logicalNot(factory.literal("0")))));
        assertEquals("22", render(testSupport, foldOnce.apply(factory.conditional(factory.literal("0"), factory.literal("11"), factory.literal("22")))));
        assertEquals("f(1)", render(testSupport, foldOnce.apply(factory.functionCall(factory.variableReference("f"), of(factory.literal("1"))))));
        assertEquals("x", render(testSupport, foldOnce.apply(factory.variableReference("x"))));
        assertEquals("4", render(testSupport, foldOnce.apply(factory.literal("4"))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void constantFolderOnceLeavesNonFoldableOrFalseyBranchesInPlace(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        var foldOnce = testSupport.v.constantFolderOnce();

        assertEquals("x + 2", render(testSupport, foldOnce.apply(factory.addition(factory.variableReference("x"), factory.literal("2")))));
        assertEquals("-x", render(testSupport, foldOnce.apply(factory.negation(factory.variableReference("x")))));
        assertEquals("x ? 11 : 22", render(testSupport, foldOnce.apply(factory.conditional(factory.variableReference("x"), factory.literal("11"), factory.literal("22")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.equality(factory.literal("2"), factory.literal("3")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.inequality(factory.literal("2"), factory.literal("2")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.lessThan(factory.literal("3"), factory.literal("2")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.greaterThan(factory.literal("2"), factory.literal("3")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.lessThanOrEqual(factory.literal("3"), factory.literal("2")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.greaterThanOrEqual(factory.literal("2"), factory.literal("3")))));
        assertEquals("x", render(testSupport, foldOnce.apply(factory.conjunction(factory.literal("1"), factory.variableReference("x")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.disjunction(factory.literal("0"), factory.literal("0")))));
        assertEquals("0", render(testSupport, foldOnce.apply(factory.logicalNot(factory.literal("1")))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void renameVariableRewritesMatchingReferencesOnly(String variant, TestSupport<E> testSupport) {
        var renamed = testSupport.v.renameVariable("x", "y").apply(testSupport.sampleTraversalExpression());

        assertEquals(testSupport.expectedRenamedTraversalRender(), render(testSupport, renamed));
    }
}