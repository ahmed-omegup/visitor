package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionCallCounter;
import port.IFactory;

class FunctionCallCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsNestedFunctionCalls() {
        assertEquals(
            2,
            new FunctionCallCounter().handle(
                factory.addition(
                    factory.functionCall(factory.variableReference("left"), factory.literal("1")),
                    factory.functionCall(factory.variableReference("right"), factory.literal("2"), factory.literal("3"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionFunctionCall() {
        assertEquals(1, new FunctionCallCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}