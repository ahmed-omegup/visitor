package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Expression;
import lib.handlers.HandlerFactory;

class IntegerEvaluationVisitorTest extends TestBase<Expression> {
    IntegerEvaluationVisitorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }

    @Test
    void evaluatesConstantArithmetic() {
        var evaluator = testSupport.v.integerEvaluator();

        assertEquals(
            4,
            evaluator.apply(factory.addition(factory.literal("3"), factory.literal("1")))
        );
    }

    @Test
    void failsQuickOnUnknownVariable() {
        var evaluator = testSupport.v.integerEvaluator();

        var exception = assertThrows(
            IllegalArgumentException.class,
            () -> evaluator.apply(factory.addition(factory.literal("3"), factory.variableReference("x")))
        );

        assertEquals("Unknown variable: x", exception.getMessage());
    }

    @Test
    void canUseProvidedVariablesAndFunctions() {
        var evaluator = testSupport.v.integerEvaluator(
            Map.of("x", 3),
            Map.of("inc", values -> values.get(0) + 1)
        );

        assertEquals(3, evaluator.apply(factory.variableReference("x")));
        assertEquals(
            4,
            evaluator.apply(factory.functionCall(factory.variableReference("inc"), List.of(factory.literal("3"))))
        );
    }
}