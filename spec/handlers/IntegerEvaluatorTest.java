package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.Expression;
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
import lib.handlers.IntegerEvaluator;

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

        Expression expression = conditional(
            variableReference("threshold"),
            addition(
                functionCall(variableReference("max"), literal("3"), variableReference("threshold")),
                multiplication(literal("2"), literal("5"))
            ),
            functionCall(variableReference("fallback"), literal("0"))
        );

        assertEquals(14, evaluator.handle(expression), "evaluator should resolve variables, conditionals, and function calls");
    }

    @Test
    void evaluatesEveryOperator() {
        var evaluator = new IntegerEvaluator(
            Map.of("x", 8, "y", 2, "zero", 0),
            Map.of("sum", values -> values.stream().mapToInt(Integer::intValue).sum())
        );

        assertEquals(10, evaluator.handle(addition(literal("8"), literal("2"))), "addition should evaluate");
        assertEquals(6, evaluator.handle(subtraction(literal("8"), literal("2"))), "subtraction should evaluate");
        assertEquals(16, evaluator.handle(multiplication(literal("8"), literal("2"))), "multiplication should evaluate");
        assertEquals(4, evaluator.handle(division(literal("8"), literal("2"))), "division should evaluate");
        assertEquals(-8, evaluator.handle(negation(literal("8"))), "negation should evaluate");
        assertEquals(0, evaluator.handle(modulo(literal("8"), literal("2"))), "modulo should evaluate");
        assertEquals(64, evaluator.handle(exponentiation(literal("8"), literal("2"))), "exponentiation should evaluate");
        assertEquals(1, evaluator.handle(equality(literal("8"), literal("8"))), "equality should evaluate");
        assertEquals(1, evaluator.handle(inequality(literal("8"), literal("2"))), "inequality should evaluate");
        assertEquals(1, evaluator.handle(lessThan(literal("2"), literal("8"))), "less-than should evaluate");
        assertEquals(1, evaluator.handle(greaterThan(literal("8"), literal("2"))), "greater-than should evaluate");
        assertEquals(1, evaluator.handle(lessThanOrEqual(literal("2"), literal("2"))), "less-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(greaterThanOrEqual(literal("8"), literal("8"))), "greater-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(conjunction(literal("1"), literal("2"))), "conjunction should evaluate");
        assertEquals(1, evaluator.handle(disjunction(literal("0"), literal("2"))), "disjunction should evaluate");
        assertEquals(1, evaluator.handle(logicalNot(literal("0"))), "logical-not should evaluate");
        assertEquals(22, evaluator.handle(conditional(literal("0"), literal("11"), literal("22"))), "conditional false branch should evaluate");
        assertEquals(12, evaluator.handle(functionCall(variableReference("sum"), literal("8"), literal("2"), literal("2"))), "function call should evaluate");
        assertEquals(8, evaluator.handle(variableReference("x")), "variable reference should evaluate");
    }

    @Test
    void rejectsUnknownVariable() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown variable: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(variableReference("missing"))).getMessage());
    }

    @Test
    void rejectsUnknownFunction() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown function: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(functionCall(variableReference("missing"), literal("1")))).getMessage());
    }

    @Test
    void rejectsNonIntegerLiteral() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        var exception = assertThrows(IllegalArgumentException.class, () -> evaluator.handle(literal("nan")));
        assertTrue(exception.getMessage().contains("Literal is not an integer: nan"), "non-integer literal should describe the failure");
    }

    @Test
    void rejectsInvalidFunctionCallee() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of("f", values -> values.get(0)));
        var invalidCall = functionCall(addition(literal("1"), literal("2")), literal("3"));

        assertEquals("Function call requires a variable reference callee", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(invalidCall)).getMessage());
    }
}