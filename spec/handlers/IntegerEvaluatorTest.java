package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;
import lib.visitors.IntegerEvaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import port.IFactory;

abstract class IntegerEvaluatorTestBase<E extends Expression> extends TestBase<E> {
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
                factory.functionCall(factory.variableReference("max"), java.util.List.of( factory.literal("3"), factory.variableReference("threshold"))),
                factory.multiplication(factory.literal("2"), factory.literal("5"))
            ),
            factory.functionCall(factory.variableReference("fallback"), java.util.List.of( factory.literal("0")))
        );

        assertEquals(14,expression.accept(evaluator), "evaluator should resolve variables, conditionals, and function calls");
    }

    @Test
    void evaluatesEveryOperator() {
        var evaluator = testSupport.v.integerEvaluator( 
            Map.of("x", 8, "y", 2, "zero", 0),
            Map.of("sum", values -> values.stream().mapToInt(Integer::intValue).sum())
        );

        assertEquals(10,factory.addition(factory.literal("8"), factory.literal("2")).accept(evaluator), "addition should evaluate");
        assertEquals(6,factory.subtraction(factory.literal("8"), factory.literal("2")).accept(evaluator), "subtraction should evaluate");
        assertEquals(16,factory.multiplication(factory.literal("8"), factory.literal("2")).accept(evaluator), "multiplication should evaluate");
        assertEquals(4,factory.division(factory.literal("8"), factory.literal("2")).accept(evaluator), "division should evaluate");
        assertEquals(-8,factory.negation(factory.literal("8")).accept(evaluator), "negation should evaluate");
        assertEquals(0,factory.modulo(factory.literal("8"), factory.literal("2")).accept(evaluator), "modulo should evaluate");
        assertEquals(64,factory.exponentiation(factory.literal("8"), factory.literal("2")).accept(evaluator), "exponentiation should evaluate");
        assertEquals(1,factory.equality(factory.literal("8"), factory.literal("8")).accept(evaluator), "equality should evaluate");
        assertEquals(1,factory.inequality(factory.literal("8"), factory.literal("2")).accept(evaluator), "inequality should evaluate");
        assertEquals(1,factory.lessThan(factory.literal("2"), factory.literal("8")).accept(evaluator), "less-than should evaluate");
        assertEquals(1,factory.greaterThan(factory.literal("8"), factory.literal("2")).accept(evaluator), "greater-than should evaluate");
        assertEquals(1,factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")).accept(evaluator), "less-than-or-equal should evaluate");
        assertEquals(1,factory.greaterThanOrEqual(factory.literal("8"), factory.literal("8")).accept(evaluator), "greater-than-or-equal should evaluate");
        assertEquals(1,factory.conjunction(factory.literal("1"), factory.literal("2")).accept(evaluator), "conjunction should evaluate");
        assertEquals(1,factory.disjunction(factory.literal("0"), factory.literal("2")).accept(evaluator), "disjunction should evaluate");
        assertEquals(1,factory.logicalNot(factory.literal("0")).accept(evaluator), "logical-not should evaluate");
        assertEquals(22,factory.conditional(factory.literal("0"), factory.literal("11"), factory.literal("22")).accept(evaluator), "conditional false branch should evaluate");
        assertEquals(12,factory.functionCall(factory.variableReference("sum"), java.util.List.of( factory.literal("8"), factory.literal("2"), factory.literal("2"))).accept(evaluator), "function call should evaluate");
        assertEquals(8,factory.variableReference("x").accept(evaluator), "variable reference should evaluate");
    }

    @Test
    void rejectsUnknownVariable() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of());

        assertEquals("Unknown variable: missing", assertThrows(IllegalArgumentException.class, () -> factory.variableReference("missing").accept(evaluator)).getMessage());
    }

    @Test
    void rejectsUnknownFunction() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of());

        assertEquals("Unknown function: missing", assertThrows(IllegalArgumentException.class, () -> factory.functionCall(factory.variableReference("missing"), java.util.List.of( factory.literal("1"))).accept(evaluator)).getMessage());
    }

    @Test
    void rejectsNonIntegerLiteral() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of());

        var exception = assertThrows(IllegalArgumentException.class, () -> factory.literal("nan").accept(evaluator));
        assertTrue(exception.getMessage().contains("Literal is not an integer: nan"), "non-integer literal should describe the failure");
    }

    @Test
    void rejectsInvalidFunctionCallee() {
        var evaluator = testSupport.v.integerEvaluator( Map.of(), Map.of("f", values -> values.get(0)));
        var invalidCall = factory.functionCall(factory.addition(factory.literal("1"), factory.literal("2")), java.util.List.of( factory.literal("3")));

        assertEquals("Function call requires a variable reference callee", assertThrows(IllegalArgumentException.class, () -> invalidCall.accept(evaluator)).getMessage());
    }
}

class IntegerEvaluatorTest extends IntegerEvaluatorTestBase<Expression> {
    IntegerEvaluatorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
