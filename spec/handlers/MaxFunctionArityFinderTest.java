package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;
import lib.handlers.MaxFunctionArityFinder;

class MaxFunctionArityFinderTest {
    @Test
    void returnsLargestFunctionArityInTree() {
        assertEquals(
            3,
            new MaxFunctionArityFinder().handle(
                addition(
                    functionCall(variableReference("ping")),
                    functionCall(variableReference("sum"), literal("1"), literal("2"), literal("3"))
                )
            )
        );
    }

    @Test
    void returnsZeroWhenNoFunctionCallExists() {
        assertEquals(0, new MaxFunctionArityFinder().handle(addition(literal("1"), literal("2"))));
    }

    @Test
    void followsTraversalExpressionToLargestArity() {
        assertEquals(7, new MaxFunctionArityFinder().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> traversesEveryOperatorShapeWithoutFunctionCalls() {
        var finder = new MaxFunctionArityFinder();
        return java.util.List.of(
            literal("1"),
            variableReference("x"),
            addition(literal("1"), literal("2")),
            subtraction(literal("1"), literal("2")),
            multiplication(literal("1"), literal("2")),
            division(literal("1"), literal("2")),
            negation(literal("1")),
            modulo(literal("1"), literal("2")),
            exponentiation(literal("1"), literal("2")),
            equality(literal("1"), literal("2")),
            inequality(literal("1"), literal("2")),
            lessThan(literal("1"), literal("2")),
            greaterThan(literal("1"), literal("2")),
            lessThanOrEqual(literal("1"), literal("2")),
            greaterThanOrEqual(literal("1"), literal("2")),
            conjunction(literal("1"), literal("2")),
            disjunction(literal("1"), literal("2")),
            logicalNot(literal("1")),
            conditional(literal("1"), literal("2"), literal("3"))
        ).stream().map(expression -> DynamicTest.dynamicTest("arity-" + expression.getClass().getSimpleName(), () ->
            assertEquals(0, finder.handle(expression)))).toList();
    }
}