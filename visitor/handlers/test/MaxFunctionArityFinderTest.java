package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import visitor.handlers.MaxFunctionArityFinder;

class MaxFunctionArityFinderTest {
    @Test
    void returnsLargestFunctionArityInTree() {
        assertEquals(
            3,
            new MaxFunctionArityFinder().handle(
                new Addition(
                    new FunctionCall(new VariableReference("ping")),
                    new FunctionCall(new VariableReference("sum"), new Literal("1"), new Literal("2"), new Literal("3"))
                )
            )
        );
    }

    @Test
    void returnsZeroWhenNoFunctionCallExists() {
        assertEquals(0, new MaxFunctionArityFinder().handle(new visitor.expression.Addition(new visitor.expression.Literal("1"), new visitor.expression.Literal("2"))));
    }

    @Test
    void followsTraversalExpressionToLargestArity() {
        assertEquals(7, new MaxFunctionArityFinder().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> traversesEveryOperatorShapeWithoutFunctionCalls() {
        var finder = new MaxFunctionArityFinder();
        return java.util.List.of(
            new Literal("1"),
            new VariableReference("x"),
            new Addition(new Literal("1"), new Literal("2")),
            new Subtraction(new Literal("1"), new Literal("2")),
            new Multiplication(new Literal("1"), new Literal("2")),
            new Division(new Literal("1"), new Literal("2")),
            new Negation(new Literal("1")),
            new Modulo(new Literal("1"), new Literal("2")),
            new Exponentiation(new Literal("1"), new Literal("2")),
            new Equality(new Literal("1"), new Literal("2")),
            new Inequality(new Literal("1"), new Literal("2")),
            new LessThan(new Literal("1"), new Literal("2")),
            new GreaterThan(new Literal("1"), new Literal("2")),
            new LessThanOrEqual(new Literal("1"), new Literal("2")),
            new GreaterThanOrEqual(new Literal("1"), new Literal("2")),
            new Conjunction(new Literal("1"), new Literal("2")),
            new Disjunction(new Literal("1"), new Literal("2")),
            new LogicalNot(new Literal("1")),
            new Conditional(new Literal("1"), new Literal("2"), new Literal("3"))
        ).stream().map(expression -> DynamicTest.dynamicTest("arity-" + expression.getClass().getSimpleName(), () ->
            assertEquals(0, finder.handle(expression)))).toList();
    }
}