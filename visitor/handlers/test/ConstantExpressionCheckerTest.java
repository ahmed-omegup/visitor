package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Disjunction;
import visitor.expression.Division;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.FunctionCall;
import visitor.expression.GreaterThan;
import visitor.expression.GreaterThanOrEqual;
import visitor.expression.Inequality;
import visitor.expression.LessThan;
import visitor.expression.LessThanOrEqual;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Modulo;
import visitor.expression.Multiplication;
import visitor.expression.Negation;
import visitor.expression.Subtraction;
import visitor.expression.VariableReference;
import visitor.handlers.ConstantExpressionChecker;

class ConstantExpressionCheckerTest {
    @Test
    void detectsConstantAndNonConstantExpressions() {
        var checker = new ConstantExpressionChecker();

        assertTrue(checker.handle(new Addition(new Literal("1"), new Literal("2"))));
        assertFalse(checker.handle(new Addition(new VariableReference("x"), new Literal("2"))));
        assertFalse(checker.handle(new FunctionCall(new VariableReference("sum"), new Literal("1"))));
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
            new Addition(new VariableReference("x"), new Literal("1")),
            new Addition(new Literal("1"), new VariableReference("x")),
            new Subtraction(new VariableReference("x"), new Literal("1")),
            new Subtraction(new Literal("1"), new VariableReference("x")),
            new Multiplication(new Literal("1"), new VariableReference("x")),
            new Multiplication(new VariableReference("x"), new Literal("1")),
            new Division(new VariableReference("x"), new Literal("1")),
            new Division(new Literal("1"), new VariableReference("x")),
            new Modulo(new Literal("1"), new VariableReference("x")),
            new Modulo(new VariableReference("x"), new Literal("1")),
            new Exponentiation(new VariableReference("x"), new Literal("2")),
            new Exponentiation(new Literal("2"), new VariableReference("x")),
            new Equality(new Literal("1"), new VariableReference("x")),
            new Equality(new VariableReference("x"), new Literal("1")),
            new Inequality(new VariableReference("x"), new Literal("1")),
            new Inequality(new Literal("1"), new VariableReference("x")),
            new LessThan(new VariableReference("x"), new Literal("1")),
            new LessThan(new Literal("1"), new VariableReference("x")),
            new GreaterThan(new Literal("1"), new VariableReference("x")),
            new GreaterThan(new VariableReference("x"), new Literal("1")),
            new LessThanOrEqual(new VariableReference("x"), new Literal("1")),
            new LessThanOrEqual(new Literal("1"), new VariableReference("x")),
            new GreaterThanOrEqual(new Literal("1"), new VariableReference("x")),
            new GreaterThanOrEqual(new VariableReference("x"), new Literal("1")),
            new Conjunction(new VariableReference("x"), new Literal("1")),
            new Conjunction(new Literal("1"), new VariableReference("x")),
            new Disjunction(new Literal("1"), new VariableReference("x")),
            new Disjunction(new VariableReference("x"), new Literal("1")),
            new Negation(new VariableReference("x")),
            new LogicalNot(new VariableReference("x")),
            new Conditional(new VariableReference("x"), new Literal("1"), new Literal("2")),
            new Conditional(new Literal("1"), new VariableReference("x"), new Literal("2")),
            new Conditional(new Literal("1"), new Literal("2"), new VariableReference("x"))
        ).stream().map(expression -> DynamicTest.dynamicTest("non-constant-" + expression.getClass().getSimpleName(), () ->
            assertFalse(checker.handle(expression)))).toList();
    }
}