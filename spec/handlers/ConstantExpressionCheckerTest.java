package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;
import lib.handlers.ConstantExpressionChecker;
import port.IFactory;

class ConstantExpressionCheckerTest {
    private final IFactory factory = new Factory();
    @Test
    void detectsConstantAndNonConstantExpressions() {
        var checker = TestSupport.handlers().constantExpressionChecker();

        assertTrue(factory.addition(factory.literal("1"), factory.literal("2")).accept(checker));
        assertFalse(factory.addition(factory.variableReference("x"), factory.literal("2")).accept(checker));
        assertFalse(factory.functionCall(factory.variableReference("sum"), factory.literal("1")).accept(checker));
    }

    @Test
    void rejectsTraversalExpressionBecauseOfVariablesAndFunctionCalls() {
        assertFalse(TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().constantExpressionChecker()));
    }

    @TestFactory
    Iterable<DynamicTest> acceptsAllNonVariableNonCallExpressionKindsFromSupport() {
        var checker = TestSupport.handlers().constantExpressionChecker();
        return TestSupport.sampleNonVariableExpressions().stream()
            .map(expression -> DynamicTest.dynamicTest(expression.getClass().getSimpleName(), () ->
                org.junit.jupiter.api.Assertions.assertEquals(!(expression instanceof FunctionCall),expression.accept(checker))))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> rejectsLeftAndRightVariableBranchesAcrossOperators() {
        var checker = TestSupport.handlers().constantExpressionChecker();
        return java.util.List.of(
            factory.addition(factory.variableReference("x"), factory.literal("1")),
            factory.addition(factory.literal("1"), factory.variableReference("x")),
            factory.subtraction(factory.variableReference("x"), factory.literal("1")),
            factory.subtraction(factory.literal("1"), factory.variableReference("x")),
            factory.multiplication(factory.literal("1"), factory.variableReference("x")),
            factory.multiplication(factory.variableReference("x"), factory.literal("1")),
            factory.division(factory.variableReference("x"), factory.literal("1")),
            factory.division(factory.literal("1"), factory.variableReference("x")),
            factory.modulo(factory.literal("1"), factory.variableReference("x")),
            factory.modulo(factory.variableReference("x"), factory.literal("1")),
            factory.exponentiation(factory.variableReference("x"), factory.literal("2")),
            factory.exponentiation(factory.literal("2"), factory.variableReference("x")),
            factory.equality(factory.literal("1"), factory.variableReference("x")),
            factory.equality(factory.variableReference("x"), factory.literal("1")),
            factory.inequality(factory.variableReference("x"), factory.literal("1")),
            factory.inequality(factory.literal("1"), factory.variableReference("x")),
            factory.lessThan(factory.variableReference("x"), factory.literal("1")),
            factory.lessThan(factory.literal("1"), factory.variableReference("x")),
            factory.greaterThan(factory.literal("1"), factory.variableReference("x")),
            factory.greaterThan(factory.variableReference("x"), factory.literal("1")),
            factory.lessThanOrEqual(factory.variableReference("x"), factory.literal("1")),
            factory.lessThanOrEqual(factory.literal("1"), factory.variableReference("x")),
            factory.greaterThanOrEqual(factory.literal("1"), factory.variableReference("x")),
            factory.greaterThanOrEqual(factory.variableReference("x"), factory.literal("1")),
            factory.conjunction(factory.variableReference("x"), factory.literal("1")),
            factory.conjunction(factory.literal("1"), factory.variableReference("x")),
            factory.disjunction(factory.literal("1"), factory.variableReference("x")),
            factory.disjunction(factory.variableReference("x"), factory.literal("1")),
            factory.negation(factory.variableReference("x")),
            factory.logicalNot(factory.variableReference("x")),
            factory.conditional(factory.variableReference("x"), factory.literal("1"), factory.literal("2")),
            factory.conditional(factory.literal("1"), factory.variableReference("x"), factory.literal("2")),
            factory.conditional(factory.literal("1"), factory.literal("2"), factory.variableReference("x"))
        ).stream().map(expression -> DynamicTest.dynamicTest("non-constant-" + expression.getClass().getSimpleName(), () ->
            assertFalse(expression.accept(checker)))).toList();
    }
}