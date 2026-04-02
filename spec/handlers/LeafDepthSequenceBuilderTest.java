package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.LeafDepthSequenceBuilder;

class LeafDepthSequenceBuilderTest {
    @Test
    void recordsLeafDepthsInEncounterOrder() {
        assertEquals(
            List.of(1, 2),
            new LeafDepthSequenceBuilder().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.negation(lib.expression.Expression.literal("2"))))
        );
    }

    @Test
    void recordsTraversalExpressionLeafDepths() {
        assertEquals(
            List.of(3, 3, 4, 4, 3, 3, 4, 4, 4, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3),
            new LeafDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression())
        );
    }
}