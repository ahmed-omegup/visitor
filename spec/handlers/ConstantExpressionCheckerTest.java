package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;
import lib.handlers.ConstantExpressionChecker;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;

abstract class ConstantExpressionCheckerTestBase<E> extends TestBase<E> {
    ConstantExpressionCheckerTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void detectsConstantAndNonConstantExpressions() {
        var checker = testSupport.v.constantExpressionChecker();

        assertTrue(checker.apply(factory.addition(factory.literal("1"), factory.literal("2"))));
        assertFalse(checker.apply(factory.addition(factory.variableReference("x"), factory.literal("2"))));
        assertFalse(checker.apply(factory.functionCall(factory.variableReference("sum"), of( factory.literal("1")))));
    }

    @Test
    void rejectsTraversalExpressionBecauseOfVariablesAndFunctionCalls() {
        assertFalse(testSupport.v.constantExpressionChecker().apply(testSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> acceptsAllNonVariableNonCallExpressionKindsFromSupport() {
        var checker = testSupport.v.constantExpressionChecker();
        return testSupport.sampleNonVariableExpressions().stream()
            .map(expression -> DynamicTest.dynamicTest(typeName(expression), () ->
                org.junit.jupiter.api.Assertions.assertEquals(!typeName(expression).equals("FunctionCall"),checker.apply(expression))))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> rejectsLeftAndRightVariableBranchesAcrossOperators() {
        var checker = testSupport.v.constantExpressionChecker();
        return of(
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
        ).stream().map(expression -> DynamicTest.dynamicTest("non-constant-" + typeName(expression), () ->
            assertFalse(checker.apply(expression)))).toList();
    }
}

class ConstantExpressionCheckerTest extends ConstantExpressionCheckerTestBase<Expression> {
    ConstantExpressionCheckerTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
