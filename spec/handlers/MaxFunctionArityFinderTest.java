package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import lib.handlers.MaxFunctionArityFinder;

class MaxFunctionArityFinderTest {
    @Test
    void returnsLargestFunctionArityInTree() {
        assertEquals(
            3,
            new MaxFunctionArityFinder().handle(
                lib.expression.ExpressionFactory.addition(
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("ping")),
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("sum"), lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3"))
                )
            )
        );
    }

    @Test
    void returnsZeroWhenNoFunctionCallExists() {
        assertEquals(0, new MaxFunctionArityFinder().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"))));
    }

    @Test
    void followsTraversalExpressionToLargestArity() {
        assertEquals(7, new MaxFunctionArityFinder().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> traversesEveryOperatorShapeWithoutFunctionCalls() {
        var finder = new MaxFunctionArityFinder();
        return java.util.List.of(
            lib.expression.ExpressionFactory.literal("1"),
            lib.expression.ExpressionFactory.variableReference("x"),
            lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.subtraction(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.multiplication(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.exponentiation(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.equality(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.inequality(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.lessThan(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.greaterThan(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.lessThanOrEqual(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.greaterThanOrEqual(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.conjunction(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.disjunction(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.logicalNot(lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3"))
        ).stream().map(expression -> DynamicTest.dynamicTest("arity-" + expression.getClass().getSimpleName(), () ->
            assertEquals(0, finder.handle(expression)))).toList();
    }
}