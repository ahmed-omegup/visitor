package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;
import lib.visitors.IntegerEvaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.*;

abstract class IntegerEvaluatorTestBase<E> extends TestBase<E> {
    IntegerEvaluatorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void evaluatesExpressionsWithVariablesAndFunctions() {
        var evaluator = testSupport.v.integerEvaluator( 
            Map.of("threshold", 4, "fallback", 9),
            Map.of(
                "max", values -> Math.max(values.get(0), values.get(1)),
                "fallback", values -> values.get(0) + 100
            )
        );

        var expression = factory.conditional(
            factory.variableReference("threshold"),
            factory.addition(
                factory.functionCall(factory.variableReference("max"), of( factory.literal("3"), factory.variableReference("threshold"))),
                factory.multiplication(factory.literal("2"), factory.literal("5"))
            ),
            factory.functionCall(factory.variableReference("fallback"), of( factory.literal("0")))
        );

        assertEquals(14,evaluator.apply(expression), "evaluator should resolve variables, conditionals, and function calls");
    }

    @Test
    void evaluatesEveryOperator() {
        var evaluator = testSupport.v.integerEvaluator( 
            Map.of("x", 8, "y", 2, "zero", 0),
            Map.of("sum", values -> values.stream().mapToInt(Integer::intValue).sum())
        );

        assertEquals(10,evaluator.apply(factory.addition(factory.literal("8"), factory.literal("2"))), "addition should evaluate");
        assertEquals(6,evaluator.apply(factory.subtraction(factory.literal("8"), factory.literal("2"))), "subtraction should evaluate");
        assertEquals(16,evaluator.apply(factory.multiplication(factory.literal("8"), factory.literal("2"))), "multiplication should evaluate");
        assertEquals(4,evaluator.apply(factory.division(factory.literal("8"), factory.literal("2"))), "division should evaluate");
        assertEquals(-8,evaluator.apply(factory.negation(factory.literal("8"))), "negation should evaluate");
        assertEquals(0,evaluator.apply(factory.modulo(factory.literal("8"), factory.literal("2"))), "modulo should evaluate");
        assertEquals(64,evaluator.apply(factory.exponentiation(factory.literal("8"), factory.literal("2"))), "exponentiation should evaluate");
        assertEquals(1,evaluator.apply(factory.equality(factory.literal("8"), factory.literal("8"))), "equality should evaluate");
        assertEquals(1,evaluator.apply(factory.inequality(factory.literal("8"), factory.literal("2"))), "inequality should evaluate");
        assertEquals(1,evaluator.apply(factory.lessThan(factory.literal("2"), factory.literal("8"))), "less-than should evaluate");
        assertEquals(1,evaluator.apply(factory.greaterThan(factory.literal("8"), factory.literal("2"))), "greater-than should evaluate");
        assertEquals(1,evaluator.apply(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2"))), "less-than-or-equal should evaluate");
        assertEquals(1,evaluator.apply(factory.greaterThanOrEqual(factory.literal("8"), factory.literal("8"))), "greater-than-or-equal should evaluate");
        assertEquals(1,evaluator.apply(factory.conjunction(factory.literal("1"), factory.literal("2"))), "conjunction should evaluate");
        assertEquals(1,evaluator.apply(factory.disjunction(factory.literal("0"), factory.literal("2"))), "disjunction should evaluate");
        assertEquals(1,evaluator.apply(factory.logicalNot(factory.literal("0"))), "logical-not should evaluate");
        assertEquals(22,evaluator.apply(factory.conditional(factory.literal("0"), factory.literal("11"), factory.literal("22"))), "conditional false branch should evaluate");
        assertEquals(12,evaluator.apply(factory.functionCall(factory.variableReference("sum"), of( factory.literal("8"), factory.literal("2"), factory.literal("2")))), "function call should evaluate");
        assertEquals(8,evaluator.apply(factory.variableReference("x")), "variable reference should evaluate");
    }

    @Test
    void rejectsUnknownVariable() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of());

        assertEquals("Unknown variable: missing", assertThrows(IllegalArgumentException.class, () ->evaluator.apply(factory.variableReference("missing"))).getMessage());
    }

    @Test
    void rejectsUnknownFunction() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of());

        assertEquals("Unknown function: missing", assertThrows(IllegalArgumentException.class, () ->evaluator.apply(factory.functionCall(factory.variableReference("missing"), of( factory.literal("1"))))).getMessage());
    }

    @Test
    void rejectsNonIntegerLiteral() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of());

        var exception = assertThrows(IllegalArgumentException.class, () ->evaluator.apply(factory.literal("nan")));
        assertTrue(exception.getMessage().contains("Literal is not an integer: nan"), "non-integer literal should describe the failure");
    }

    @Test
    void rejectsInvalidFunctionCallee() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of("f", values -> values.get(0)));
        var invalidCall = factory.functionCall(factory.addition(factory.literal("1"), factory.literal("2")), of( factory.literal("3")));

        assertEquals("Function call requires a variable reference callee", assertThrows(IllegalArgumentException.class, () ->evaluator.apply(invalidCall)).getMessage());
    }
}

class IntegerEvaluatorTest extends IntegerEvaluatorTestBase<Expression> {
    IntegerEvaluatorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
