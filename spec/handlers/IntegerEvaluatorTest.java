package spec.handlers;

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

        Expression expression = lib.expression.Expression.conditional(
            lib.expression.Expression.variableReference("threshold"),
            lib.expression.Expression.addition(
                lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("max"), lib.expression.Expression.literal("3"), lib.expression.Expression.variableReference("threshold")),
                lib.expression.Expression.multiplication(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("5"))
            ),
            lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("fallback"), lib.expression.Expression.literal("0"))
        );

        assertEquals(14, evaluator.handle(expression), "evaluator should resolve variables, conditionals, and function calls");
    }

    @Test
    void evaluatesEveryOperator() {
        var evaluator = new IntegerEvaluator(
            Map.of("x", 8, "y", 2, "zero", 0),
            Map.of("sum", values -> values.stream().mapToInt(Integer::intValue).sum())
        );

        assertEquals(10, evaluator.handle(lib.expression.Expression.addition(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "addition should evaluate");
        assertEquals(6, evaluator.handle(lib.expression.Expression.subtraction(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "subtraction should evaluate");
        assertEquals(16, evaluator.handle(lib.expression.Expression.multiplication(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "multiplication should evaluate");
        assertEquals(4, evaluator.handle(lib.expression.Expression.division(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "division should evaluate");
        assertEquals(-8, evaluator.handle(lib.expression.Expression.negation(lib.expression.Expression.literal("8"))), "negation should evaluate");
        assertEquals(0, evaluator.handle(lib.expression.Expression.modulo(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "modulo should evaluate");
        assertEquals(64, evaluator.handle(lib.expression.Expression.exponentiation(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "exponentiation should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.equality(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("8"))), "equality should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.inequality(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "inequality should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.lessThan(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("8"))), "less-than should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.greaterThan(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "greater-than should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.lessThanOrEqual(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2"))), "less-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.greaterThanOrEqual(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("8"))), "greater-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.conjunction(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"))), "conjunction should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.disjunction(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("2"))), "disjunction should evaluate");
        assertEquals(1, evaluator.handle(lib.expression.Expression.logicalNot(lib.expression.Expression.literal("0"))), "logical-not should evaluate");
        assertEquals(22, evaluator.handle(lib.expression.Expression.conditional(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("11"), lib.expression.Expression.literal("22"))), "conditional false branch should evaluate");
        assertEquals(12, evaluator.handle(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2"))), "function call should evaluate");
        assertEquals(8, evaluator.handle(lib.expression.Expression.variableReference("x")), "variable reference should evaluate");
    }

    @Test
    void rejectsUnknownVariable() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown variable: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(lib.expression.Expression.variableReference("missing"))).getMessage());
    }

    @Test
    void rejectsUnknownFunction() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown function: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("missing"), lib.expression.Expression.literal("1")))).getMessage());
    }

    @Test
    void rejectsNonIntegerLiteral() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        var exception = assertThrows(IllegalArgumentException.class, () -> evaluator.handle(lib.expression.Expression.literal("nan")));
        assertTrue(exception.getMessage().contains("Literal is not an integer: nan"), "non-integer literal should describe the failure");
    }

    @Test
    void rejectsInvalidFunctionCallee() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of("f", values -> values.get(0)));
        var invalidCall = lib.expression.Expression.functionCall(lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")), lib.expression.Expression.literal("3"));

        assertEquals("Function call requires a variable reference callee", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(invalidCall)).getMessage());
    }
}