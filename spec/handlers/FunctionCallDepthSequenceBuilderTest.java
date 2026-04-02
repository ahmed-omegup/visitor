package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionCallDepthSequenceBuilder;

class FunctionCallDepthSequenceBuilderTest {
    @Test
    void recordsFunctionCallDepthsInEncounterOrder() {
        assertEquals(List.of(1), new FunctionCallDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            List.of(0, 1),
            new FunctionCallDepthSequenceBuilder().handle(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("f")), lib.expression.ExpressionFactory.literal("1")))
        );
    }
}