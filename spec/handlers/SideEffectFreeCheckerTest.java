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
import lib.handlers.SideEffectFreeChecker;

class SideEffectFreeCheckerTest {
    @Test
    void treatsFunctionCallsAsSideEffectCandidates() {
        var checker = new SideEffectFreeChecker();

        assertTrue(checker.handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("1"))));
        assertFalse(checker.handle(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1"))));
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
        var call = lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1"));
        return java.util.List.of(
            lib.expression.Expression.addition(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.addition(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.subtraction(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.subtraction(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.multiplication(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.multiplication(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.division(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.division(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.modulo(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.modulo(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.exponentiation(call, lib.expression.Expression.literal("2")),
            lib.expression.Expression.exponentiation(lib.expression.Expression.literal("2"), call),
            lib.expression.Expression.equality(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.equality(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.inequality(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.inequality(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.lessThan(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.lessThan(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.greaterThan(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.greaterThan(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.lessThanOrEqual(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.lessThanOrEqual(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.greaterThanOrEqual(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.greaterThanOrEqual(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.conjunction(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.conjunction(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.disjunction(lib.expression.Expression.literal("1"), call),
            lib.expression.Expression.disjunction(call, lib.expression.Expression.literal("1")),
            lib.expression.Expression.negation(call),
            lib.expression.Expression.logicalNot(call),
            lib.expression.Expression.conditional(call, lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")),
            lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), call, lib.expression.Expression.literal("2")),
            lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"), call)
        ).stream().map(expression -> DynamicTest.dynamicTest("side-effect-" + expression.getClass().getSimpleName(), () ->
            assertFalse(checker.handle(expression)))).toList();
    }
}