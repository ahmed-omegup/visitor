package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;
import lib.handlers.MaxFunctionArityFinder;

class MaxFunctionArityFinderTest {
    private final Factory factory = new Factory();
    @Test
    void returnsLargestFunctionArityInTree() {
        assertEquals(
            3,
            new MaxFunctionArityFinder().handle(
                factory.addition(
                    factory.functionCall(factory.variableReference("ping")),
                    factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2"), factory.literal("3"))
                )
            )
        );
    }

    @Test
    void returnsZeroWhenNoFunctionCallExists() {
        assertEquals(0, new MaxFunctionArityFinder().handle(factory.addition(factory.literal("1"), factory.literal("2"))));
    }

    @Test
    void followsTraversalExpressionToLargestArity() {
        assertEquals(7, new MaxFunctionArityFinder().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> traversesEveryOperatorShapeWithoutFunctionCalls() {
        var finder = new MaxFunctionArityFinder();
        return java.util.List.of(
            factory.literal("1"),
            factory.variableReference("x"),
            factory.addition(factory.literal("1"), factory.literal("2")),
            factory.subtraction(factory.literal("1"), factory.literal("2")),
            factory.multiplication(factory.literal("1"), factory.literal("2")),
            factory.division(factory.literal("1"), factory.literal("2")),
            factory.negation(factory.literal("1")),
            factory.modulo(factory.literal("1"), factory.literal("2")),
            factory.exponentiation(factory.literal("1"), factory.literal("2")),
            factory.equality(factory.literal("1"), factory.literal("2")),
            factory.inequality(factory.literal("1"), factory.literal("2")),
            factory.lessThan(factory.literal("1"), factory.literal("2")),
            factory.greaterThan(factory.literal("1"), factory.literal("2")),
            factory.lessThanOrEqual(factory.literal("1"), factory.literal("2")),
            factory.greaterThanOrEqual(factory.literal("1"), factory.literal("2")),
            factory.conjunction(factory.literal("1"), factory.literal("2")),
            factory.disjunction(factory.literal("1"), factory.literal("2")),
            factory.logicalNot(factory.literal("1")),
            factory.conditional(factory.literal("1"), factory.literal("2"), factory.literal("3"))
        ).stream().map(expression -> DynamicTest.dynamicTest("arity-" + expression.getClass().getSimpleName(), () ->
            assertEquals(0, finder.handle(expression)))).toList();
    }
}