package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;

class SideEffectFreeCheckerTest {
    @Test
    void treatsFunctionCallsAsSideEffectCandidates() {
        var checker = new SideEffectFreeChecker();

        assertTrue(checker.handle(addition(variableReference("x"), literal("1"))));
        assertFalse(checker.handle(functionCall(variableReference("sum"), literal("1"))));
    }

    @Test
    void rejectsTraversalExpressionBecauseOfFunctionCall() {
        assertFalse(new SideEffectFreeChecker().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> acceptsAllNonCallExpressionKindsFromSupport() {
        var checker = new SideEffectFreeChecker();
        return TestSupport.sampleNonVariableExpressions().stream()
            .map(expression -> DynamicTest.dynamicTest(expression.getClass().getSimpleName(), () ->
                org.junit.jupiter.api.Assertions.assertEquals(!(expression instanceof FunctionCall), checker.handle(expression))))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> rejectsEmbeddedFunctionCallsAcrossOperators() {
        var checker = new SideEffectFreeChecker();
        var call = functionCall(variableReference("sum"), literal("1"));
        return java.util.List.of(
            addition(call, literal("1")),
            addition(literal("1"), call),
            subtraction(call, literal("1")),
            subtraction(literal("1"), call),
            multiplication(literal("1"), call),
            multiplication(call, literal("1")),
            division(call, literal("1")),
            division(literal("1"), call),
            modulo(literal("1"), call),
            modulo(call, literal("1")),
            exponentiation(call, literal("2")),
            exponentiation(literal("2"), call),
            equality(literal("1"), call),
            equality(call, literal("1")),
            inequality(call, literal("1")),
            inequality(literal("1"), call),
            lessThan(call, literal("1")),
            lessThan(literal("1"), call),
            greaterThan(literal("1"), call),
            greaterThan(call, literal("1")),
            lessThanOrEqual(call, literal("1")),
            lessThanOrEqual(literal("1"), call),
            greaterThanOrEqual(literal("1"), call),
            greaterThanOrEqual(call, literal("1")),
            conjunction(call, literal("1")),
            conjunction(literal("1"), call),
            disjunction(literal("1"), call),
            disjunction(call, literal("1")),
            negation(call),
            logicalNot(call),
            conditional(call, literal("1"), literal("2")),
            conditional(literal("1"), call, literal("2")),
            conditional(literal("1"), literal("2"), call)
        ).stream().map(expression -> DynamicTest.dynamicTest("side-effect-" + expression.getClass().getSimpleName(), () ->
            assertFalse(checker.handle(expression)))).toList();
    }
}