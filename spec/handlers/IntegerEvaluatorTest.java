package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.handlers.IntegerEvaluator;
import port.IFactory;

class IntegerEvaluatorTest {
    private final IFactory factory = new Factory();
    @Test
    void evaluatesExpressionsWithVariablesAndFunctions() {
        var evaluator = new IntegerEvaluator(
            Map.of("threshold", 4, "fallback", 9),
            Map.of(
                "max", values -> Math.max(values.get(0), values.get(1)),
                "fallback", values -> values.get(0) + 100
            )
        );

        Expression expression = factory.conditional(
            factory.variableReference("threshold"),
            factory.addition(
                factory.functionCall(factory.variableReference("max"), factory.literal("3"), factory.variableReference("threshold")),
                factory.multiplication(factory.literal("2"), factory.literal("5"))
            ),
            factory.functionCall(factory.variableReference("fallback"), factory.literal("0"))
        );

        assertEquals(14, evaluator.handle(expression), "evaluator should resolve variables, conditionals, and function calls");
    }

    @Test
    void evaluatesEveryOperator() {
        var evaluator = new IntegerEvaluator(
            Map.of("x", 8, "y", 2, "zero", 0),
            Map.of("sum", values -> values.stream().mapToInt(Integer::intValue).sum())
        );

        assertEquals(10, evaluator.handle(factory.addition(factory.literal("8"), factory.literal("2"))), "addition should evaluate");
        assertEquals(6, evaluator.handle(factory.subtraction(factory.literal("8"), factory.literal("2"))), "subtraction should evaluate");
        assertEquals(16, evaluator.handle(factory.multiplication(factory.literal("8"), factory.literal("2"))), "multiplication should evaluate");
        assertEquals(4, evaluator.handle(factory.division(factory.literal("8"), factory.literal("2"))), "division should evaluate");
        assertEquals(-8, evaluator.handle(factory.negation(factory.literal("8"))), "negation should evaluate");
        assertEquals(0, evaluator.handle(factory.modulo(factory.literal("8"), factory.literal("2"))), "modulo should evaluate");
        assertEquals(64, evaluator.handle(factory.exponentiation(factory.literal("8"), factory.literal("2"))), "exponentiation should evaluate");
        assertEquals(1, evaluator.handle(factory.equality(factory.literal("8"), factory.literal("8"))), "equality should evaluate");
        assertEquals(1, evaluator.handle(factory.inequality(factory.literal("8"), factory.literal("2"))), "inequality should evaluate");
        assertEquals(1, evaluator.handle(factory.lessThan(factory.literal("2"), factory.literal("8"))), "less-than should evaluate");
        assertEquals(1, evaluator.handle(factory.greaterThan(factory.literal("8"), factory.literal("2"))), "greater-than should evaluate");
        assertEquals(1, evaluator.handle(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2"))), "less-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(factory.greaterThanOrEqual(factory.literal("8"), factory.literal("8"))), "greater-than-or-equal should evaluate");
        assertEquals(1, evaluator.handle(factory.conjunction(factory.literal("1"), factory.literal("2"))), "conjunction should evaluate");
        assertEquals(1, evaluator.handle(factory.disjunction(factory.literal("0"), factory.literal("2"))), "disjunction should evaluate");
        assertEquals(1, evaluator.handle(factory.logicalNot(factory.literal("0"))), "logical-not should evaluate");
        assertEquals(22, evaluator.handle(factory.conditional(factory.literal("0"), factory.literal("11"), factory.literal("22"))), "conditional false branch should evaluate");
        assertEquals(12, evaluator.handle(factory.functionCall(factory.variableReference("sum"), factory.literal("8"), factory.literal("2"), factory.literal("2"))), "function call should evaluate");
        assertEquals(8, evaluator.handle(factory.variableReference("x")), "variable reference should evaluate");
    }

    @Test
    void rejectsUnknownVariable() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown variable: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(factory.variableReference("missing"))).getMessage());
    }

    @Test
    void rejectsUnknownFunction() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        assertEquals("Unknown function: missing", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(factory.functionCall(factory.variableReference("missing"), factory.literal("1")))).getMessage());
    }

    @Test
    void rejectsNonIntegerLiteral() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of());

        var exception = assertThrows(IllegalArgumentException.class, () -> evaluator.handle(factory.literal("nan")));
        assertTrue(exception.getMessage().contains("Literal is not an integer: nan"), "non-integer literal should describe the failure");
    }

    @Test
    void rejectsInvalidFunctionCallee() {
        var evaluator = new IntegerEvaluator(Map.of(), Map.of("f", values -> values.get(0)));
        var invalidCall = factory.functionCall(factory.addition(factory.literal("1"), factory.literal("2")), factory.literal("3"));

        assertEquals("Function call requires a variable reference callee", assertThrows(IllegalArgumentException.class, () -> evaluator.handle(invalidCall)).getMessage());
    }
}