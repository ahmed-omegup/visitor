package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.FunctionCallDepthSequenceBuilder;

class FunctionCallDepthSequenceBuilderTest {
    @Test
    void recordsFunctionCallDepthsInEncounterOrder() {
        assertEquals(List.of(1), new FunctionCallDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            List.of(0, 1),
            new FunctionCallDepthSequenceBuilder().handle(new FunctionCall(new FunctionCall(new VariableReference("f")), new Literal("1")))
        );
    }
}