package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionCallDepthSequenceBuilder;
import port.IFactory;

class FunctionCallDepthSequenceBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void recordsFunctionCallDepthsInEncounterOrder() {
        assertEquals(List.of(1),sampleTraversalExpression().accept(v.functionCallDepthSequenceBuilder()));
        assertEquals(
            List.of(0, 1),
factory.functionCall(factory.functionCall(factory.variableReference("f")), factory.literal("1")).accept(v.functionCallDepthSequenceBuilder())
        );
    }
}