package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;
import lib.visitors.MaxFunctionArityFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;

abstract class MaxFunctionArityFinderTestBase<E> extends TestBase<E> {
    MaxFunctionArityFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void returnsLargestFunctionArityInTree() {
        assertEquals(
            3,testSupport.v.maxFunctionArityFinder().apply(factory.addition(
                    factory.functionCall(factory.variableReference("ping"), of()),
                    factory.functionCall(factory.variableReference("sum"), of( factory.literal("1"), factory.literal("2"), factory.literal("3")))
                ))
        );
    }

    @Test
    void returnsZeroWhenNoFunctionCallExists() {
        assertEquals(0,testSupport.v.maxFunctionArityFinder().apply(factory.addition(factory.literal("1"), factory.literal("2"))));
    }

    @Test
    void followsTraversalExpressionToLargestArity() {
        assertEquals(7,testSupport.v.maxFunctionArityFinder().apply(testSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> traversesEveryOperatorShapeWithoutFunctionCalls() {
        var finder = testSupport.v.maxFunctionArityFinder();
        return of(
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
        ).stream().map(expression -> DynamicTest.dynamicTest("arity-" + typeName(expression), () ->
            assertEquals(0,finder.apply(expression)))).toList();
    }
}

class MaxFunctionArityFinderTest extends MaxFunctionArityFinderTestBase<Expression> {
    MaxFunctionArityFinderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
