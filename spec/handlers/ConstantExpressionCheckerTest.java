package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;

class ConstantExpressionCheckerTest {
    @Test
    void detectsConstantAndNonConstantExpressions() {
        var checker = new ConstantExpressionChecker();

        assertTrue(checker.handle(addition(literal("1"), literal("2"))));
        assertFalse(checker.handle(addition(variableReference("x"), literal("2"))));
        assertFalse(checker.handle(functionCall(variableReference("sum"), literal("1"))));
    }

    @Test
    void rejectsTraversalExpressionBecauseOfVariablesAndFunctionCalls() {
        assertFalse(new ConstantExpressionChecker().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> acceptsAllNonVariableNonCallExpressionKindsFromSupport() {
        var checker = new ConstantExpressionChecker();
        return TestSupport.sampleNonVariableExpressions().stream()
            .map(expression -> DynamicTest.dynamicTest(expression.getClass().getSimpleName(), () ->
                org.junit.jupiter.api.Assertions.assertEquals(!(expression instanceof FunctionCall), checker.handle(expression))))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> rejectsLeftAndRightVariableBranchesAcrossOperators() {
        var checker = new ConstantExpressionChecker();
        return java.util.List.of(
            addition(variableReference("x"), literal("1")),
            addition(literal("1"), variableReference("x")),
            subtraction(variableReference("x"), literal("1")),
            subtraction(literal("1"), variableReference("x")),
            multiplication(literal("1"), variableReference("x")),
            multiplication(variableReference("x"), literal("1")),
            division(variableReference("x"), literal("1")),
            division(literal("1"), variableReference("x")),
            modulo(literal("1"), variableReference("x")),
            modulo(variableReference("x"), literal("1")),
            exponentiation(variableReference("x"), literal("2")),
            exponentiation(literal("2"), variableReference("x")),
            equality(literal("1"), variableReference("x")),
            equality(variableReference("x"), literal("1")),
            inequality(variableReference("x"), literal("1")),
            inequality(literal("1"), variableReference("x")),
            lessThan(variableReference("x"), literal("1")),
            lessThan(literal("1"), variableReference("x")),
            greaterThan(literal("1"), variableReference("x")),
            greaterThan(variableReference("x"), literal("1")),
            lessThanOrEqual(variableReference("x"), literal("1")),
            lessThanOrEqual(literal("1"), variableReference("x")),
            greaterThanOrEqual(literal("1"), variableReference("x")),
            greaterThanOrEqual(variableReference("x"), literal("1")),
            conjunction(variableReference("x"), literal("1")),
            conjunction(literal("1"), variableReference("x")),
            disjunction(literal("1"), variableReference("x")),
            disjunction(variableReference("x"), literal("1")),
            negation(variableReference("x")),
            logicalNot(variableReference("x")),
            conditional(variableReference("x"), literal("1"), literal("2")),
            conditional(literal("1"), variableReference("x"), literal("2")),
            conditional(literal("1"), literal("2"), variableReference("x"))
        ).stream().map(expression -> DynamicTest.dynamicTest("non-constant-" + expression.getClass().getSimpleName(), () ->
            assertFalse(checker.handle(expression)))).toList();
    }
}