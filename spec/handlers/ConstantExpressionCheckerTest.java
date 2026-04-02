package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.FunctionCall;
import lib.expression.GreaterThan;
import lib.expression.GreaterThanOrEqual;
import lib.expression.Inequality;
import lib.expression.LessThan;
import lib.expression.LessThanOrEqual;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Modulo;
import lib.expression.Multiplication;
import lib.expression.Negation;
import lib.expression.Subtraction;
import lib.expression.VariableReference;
import lib.handlers.ConstantExpressionChecker;

class ConstantExpressionCheckerTest {
    @Test
    void detectsConstantAndNonConstantExpressions() {
        var checker = new ConstantExpressionChecker();

        assertTrue(checker.handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"))));
        assertFalse(checker.handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("2"))));
        assertFalse(checker.handle(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("sum"), lib.expression.ExpressionFactory.literal("1"))));
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
            lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.subtraction(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.subtraction(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.multiplication(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.multiplication(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.exponentiation(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.exponentiation(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.equality(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.equality(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.inequality(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.inequality(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.lessThan(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.lessThan(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.greaterThan(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.greaterThan(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.lessThanOrEqual(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.lessThanOrEqual(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.greaterThanOrEqual(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.greaterThanOrEqual(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.conjunction(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.conjunction(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.disjunction(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.disjunction(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.logicalNot(lib.expression.ExpressionFactory.variableReference("x")),
            lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.variableReference("x"))
        ).stream().map(expression -> DynamicTest.dynamicTest("non-constant-" + expression.getClass().getSimpleName(), () ->
            assertFalse(checker.handle(expression)))).toList();
    }
}