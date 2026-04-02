package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Disjunction;
import visitor.expression.Division;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.Expression;
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
import visitor.handlers.IntegerEvaluator;

class IntegerEvaluatorTest {
    @Test
    void evaluatesExpressionsWithVariablesAndFunctions() {
        var evaluator = new IntegerEvaluator(
            Map.of("threshold", 4, "fallback", 9),
            Map.of(
                "max", values -> Math.max(values.get(0), values.get(1)),
                "fallback", values -> values.get(0) + 100
            )
        );

        Expression expression = new Conditional(
            new VariableReference("threshold"),
            new Addition(
                new FunctionCall(new VariableReference("max"), new Literal("3"), new VariableReference("threshold")),
                new Multiplication(new Literal("2"), new Literal("5"))
            ),
            new FunctionCall(new VariableReference("fallback"), new Literal("0"))
        );

        assertEquals(14, evaluator.handle(expression), "evaluator should resolve variables, conditionals, and function calls");
    }

    @Test
    void evaluatesEveryOperator() {
        var evaluator = new IntegerEvaluator(
            Map.of("x", 8, "y", 2, "zero", 0),
            Map.of("sum", values -> values.stream().mapToInt(Integer::intValue).sum())
        );

        assertEquals(10, evaluator.handle(new Addition(new Literal("8"), new Literal("2"))), "addition should evaluate");
        assertEquals(6, evaluator.handle(new Subtraction(new Literal("8"), new Literal("2"))), "subtraction should evaluate");
        assertEquals(16, evaluator.handle(new Multiplication(new Literal("8"), new Literal("2"))), "multiplication should evaluate");
        assertEquals(4, evaluator.handle(new Division(new Literal("8"), new Literal("2"))), "division should evaluate");
        assertEquals(-8, evaluator.handle(new Negation(new Literal("8"))), "negation should evaluate");
        assertEquals(0, evaluator.handle(new Modulo(new Literal("8"), new Literal("2"))), "modulo should evaluate");
        assertEquals(64, evaluator.handle(new Exponentiation(new Literal("8"), new Literal("2"))), "exponentiation should evaluate");
        assertEquals(1, evaluator.handle(new Equality(new Literal("8"), new Literal("8"))), "equality should evaluate");
        assertEquals(1, evaluator.handle(new Inequality(new Literal("8"), new Literal("2"))), "inequality should evaluate");
        assertEquals(1, evaluator.handle(new LessThan(new Literal("2"), new Literal("8"))), "less-than should evaluate");
        assertEquals(1, evaluator.handle(new GreaterThan(new Literal("8"), new Literal("2"))), "greater-than should evaluate");
        assertEquals(1, evaluator.handle(new LessThanOrEqual(new Literal("2"), new Literal("2"))), "less-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(new GreaterThanOrEqual(new Literal("8"), new Literal("8"))), "greater-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(new Conjunction(new Literal("1"), new Literal("2"))), "conjunction should evaluate");
        assertEquals(1, evaluator.handle(new Disjunction(new Literal("0"), new Literal("2"))), "disjunction should evaluate");
        assertEquals(1, evaluator.handle(new LogicalNot(new Literal("0"))), "logical-not should evaluate");
        assertEquals(22, evaluator.handle(new Conditional(new Literal("0"), new Literal("11"), new Literal("22"))), "conditional false branch should evaluate");
        assertEquals(12, evaluator.handle(new FunctionCall(new VariableReference("sum"), new Literal("8"), new Literal("2"), new Literal("2"))), "function call should evaluate");
        assertEquals(8, evaluator.handle(new VariableReference("x")), "variable reference should evaluate");
    }

    @Test
    void rejectsUnknownVariable() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown variable: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(new VariableReference("missing"))).getMessage());
    }

    @Test
    void rejectsUnknownFunction() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown function: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(new FunctionCall(new VariableReference("missing"), new Literal("1")))).getMessage());
    }

    @Test
    void rejectsNonIntegerLiteral() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        var exception = assertThrows(IllegalArgumentException.class, () -> evaluator.handle(new Literal("nan")));
        assertTrue(exception.getMessage().contains("Literal is not an integer: nan"), "non-integer literal should describe the failure");
    }

    @Test
    void rejectsInvalidFunctionCallee() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of("f", values -> values.get(0)));
        var invalidCall = new FunctionCall(new Addition(new Literal("1"), new Literal("2")), new Literal("3"));

        assertEquals("Function call requires a variable reference callee", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(invalidCall)).getMessage());
    }
}