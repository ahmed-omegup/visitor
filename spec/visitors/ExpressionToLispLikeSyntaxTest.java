package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lib.expression.*;

class ExpressionToLispLikeSyntaxTest {
    private static <E> String renderLispLike(TestSupport<E> testSupport, E expression) {
        return testSupport.v.lispLikeSyntaxPrinter().apply(expression);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void lispLikeSyntaxUsesPrefixForms(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        assertEquals(
            "(+ 1 (* 2 3))",
            renderLispLike(testSupport, factory.addition(factory.literal("1"), factory.multiplication(factory.literal("2"), factory.literal("3"))))
        );
        assertEquals(
            "(if (<= x 10) (f 1 y) (or (not ready) (pow 2 3)))",
            renderLispLike(testSupport, factory.conditional(
                factory.lessThanOrEqual(factory.variableReference("x"), factory.literal("10")),
                factory.functionCall(factory.variableReference("f"), java.util.List.of(factory.literal("1"), factory.variableReference("y"))),
                factory.disjunction(factory.logicalNot(factory.variableReference("ready")), factory.exponentiation(factory.literal("2"), factory.literal("3")))
            ))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void lispLikeSyntaxPrintsEveryOperatorForm(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        assertEquals("(- 3 1)", renderLispLike(testSupport, factory.subtraction(factory.literal("3"), factory.literal("1"))));
        assertEquals("(/ 6 2)", renderLispLike(testSupport, factory.division(factory.literal("6"), factory.literal("2"))));
        assertEquals("(neg 3)", renderLispLike(testSupport, factory.negation(factory.literal("3"))));
        assertEquals("(mod 7 3)", renderLispLike(testSupport, factory.modulo(factory.literal("7"), factory.literal("3"))));
        assertEquals("(= 1 1)", renderLispLike(testSupport, factory.equality(factory.literal("1"), factory.literal("1"))));
        assertEquals("(!= 1 2)", renderLispLike(testSupport, factory.inequality(factory.literal("1"), factory.literal("2"))));
        assertEquals("(< 1 2)", renderLispLike(testSupport, factory.lessThan(factory.literal("1"), factory.literal("2"))));
        assertEquals("(> 2 1)", renderLispLike(testSupport, factory.greaterThan(factory.literal("2"), factory.literal("1"))));
        assertEquals("(>= 2 2)", renderLispLike(testSupport, factory.greaterThanOrEqual(factory.literal("2"), factory.literal("2"))));
        assertEquals("(and 1 1)", renderLispLike(testSupport, factory.conjunction(factory.literal("1"), factory.literal("1"))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void lispLikeSyntaxPrintsLeavesAndCallShapes(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        assertEquals("7", renderLispLike(testSupport, factory.literal("7")));
        assertEquals("name", renderLispLike(testSupport, factory.variableReference("name")));
        assertEquals("(sum 1 2)", renderLispLike(testSupport, factory.functionCall(factory.variableReference("sum"), of(factory.literal("1"), factory.literal("2")))));
        assertEquals("(noop)", renderLispLike(testSupport, factory.functionCall(factory.variableReference("noop"), of())));
    }
}