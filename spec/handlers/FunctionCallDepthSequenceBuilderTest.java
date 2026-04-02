package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionCallDepthSequenceBuilder;
import port.IFactory;

class FunctionCallDepthSequenceBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void recordsFunctionCallDepthsInEncounterOrder() {
        assertEquals(List.of(1), new FunctionCallDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            List.of(0, 1),
            new FunctionCallDepthSequenceBuilder().handle(factory.functionCall(factory.functionCall(factory.variableReference("f")), factory.literal("1")))
        );
    }
}