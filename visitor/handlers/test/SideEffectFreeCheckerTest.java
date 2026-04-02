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
import visitor.handlers.SideEffectFreeChecker;

class SideEffectFreeCheckerTest {
    @Test
    void treatsFunctionCallsAsSideEffectCandidates() {
        var checker = new SideEffectFreeChecker();

        assertTrue(checker.handle(new Addition(new VariableReference("x"), new Literal("1"))));
        assertFalse(checker.handle(new FunctionCall(new VariableReference("sum"), new Literal("1"))));
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
        var call = new FunctionCall(new VariableReference("sum"), new Literal("1"));
        return java.util.List.of(
            new Addition(call, new Literal("1")),
            new Addition(new Literal("1"), call),
            new Subtraction(call, new Literal("1")),
            new Multiplication(new Literal("1"), call),
            new Division(call, new Literal("1")),
            new Modulo(new Literal("1"), call),
            new Exponentiation(call, new Literal("2")),
            new Equality(new Literal("1"), call),
            new Inequality(call, new Literal("1")),
            new LessThan(call, new Literal("1")),
            new GreaterThan(new Literal("1"), call),
            new LessThanOrEqual(call, new Literal("1")),
            new GreaterThanOrEqual(new Literal("1"), call),
            new Conjunction(call, new Literal("1")),
            new Disjunction(new Literal("1"), call),
            new Negation(call),
            new LogicalNot(call),
            new Conditional(call, new Literal("1"), new Literal("2")),
            new Conditional(new Literal("1"), call, new Literal("2")),
            new Conditional(new Literal("1"), new Literal("2"), call)
        ).stream().map(expression -> DynamicTest.dynamicTest("side-effect-" + expression.getClass().getSimpleName(), () ->
            assertFalse(checker.handle(expression)))).toList();
    }
}