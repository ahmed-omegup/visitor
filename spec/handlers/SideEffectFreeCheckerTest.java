package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;
import lib.visitors.SideEffectFreeChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;

abstract class SideEffectFreeCheckerTestBase<E> extends TestBase<E> {
    SideEffectFreeCheckerTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void treatsFunctionCallsAsSideEffectCandidates() {
        var checker = testSupport.v.sideEffectFreeChecker();

        assertTrue(checker.apply(factory.addition(factory.variableReference("x"), factory.literal("1"))));
        assertFalse(checker.apply(factory.functionCall(factory.variableReference("sum"), of( factory.literal("1")))));
    }

    @Test
    void rejectsTraversalExpressionBecauseOfFunctionCall() {
        assertFalse(testSupport.v.sideEffectFreeChecker().apply(testSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> acceptsAllNonCallExpressionKindsFromSupport() {
        var checker = testSupport.v.sideEffectFreeChecker();
        return testSupport.sampleNonVariableExpressions().stream()
            .map(expression -> DynamicTest.dynamicTest(expression.getClass().getSimpleName(), () ->
                org.junit.jupiter.api.Assertions.assertEquals(!(expression instanceof FunctionCall),checker.apply(expression))))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> rejectsEmbeddedFunctionCallsAcrossOperators() {
        var checker = testSupport.v.sideEffectFreeChecker();
        var call = factory.functionCall(factory.variableReference("sum"), of( factory.literal("1")));
        return of(
            factory.addition(call, factory.literal("1")),
            factory.addition(factory.literal("1"), call),
            factory.subtraction(call, factory.literal("1")),
            factory.subtraction(factory.literal("1"), call),
            factory.multiplication(factory.literal("1"), call),
            factory.multiplication(call, factory.literal("1")),
            factory.division(call, factory.literal("1")),
            factory.division(factory.literal("1"), call),
            factory.modulo(factory.literal("1"), call),
            factory.modulo(call, factory.literal("1")),
            factory.exponentiation(call, factory.literal("2")),
            factory.exponentiation(factory.literal("2"), call),
            factory.equality(factory.literal("1"), call),
            factory.equality(call, factory.literal("1")),
            factory.inequality(call, factory.literal("1")),
            factory.inequality(factory.literal("1"), call),
            factory.lessThan(call, factory.literal("1")),
            factory.lessThan(factory.literal("1"), call),
            factory.greaterThan(factory.literal("1"), call),
            factory.greaterThan(call, factory.literal("1")),
            factory.lessThanOrEqual(call, factory.literal("1")),
            factory.lessThanOrEqual(factory.literal("1"), call),
            factory.greaterThanOrEqual(factory.literal("1"), call),
            factory.greaterThanOrEqual(call, factory.literal("1")),
            factory.conjunction(call, factory.literal("1")),
            factory.conjunction(factory.literal("1"), call),
            factory.disjunction(factory.literal("1"), call),
            factory.disjunction(call, factory.literal("1")),
            factory.negation(call),
            factory.logicalNot(call),
            factory.conditional(call, factory.literal("1"), factory.literal("2")),
            factory.conditional(factory.literal("1"), call, factory.literal("2")),
            factory.conditional(factory.literal("1"), factory.literal("2"), call)
        ).stream().map(expression -> DynamicTest.dynamicTest("side-effect-" + expression.getClass().getSimpleName(), () ->
            assertFalse(checker.apply(expression)))).toList();
    }
}

class SideEffectFreeCheckerTest extends SideEffectFreeCheckerTestBase<Expression> {
    SideEffectFreeCheckerTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
