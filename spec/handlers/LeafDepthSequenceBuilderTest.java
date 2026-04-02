package spec.handlers;

import static lib.expression.Factory.*;

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
            new LeafDepthSequenceBuilder().handle(addition(variableReference("x"), negation(literal("2"))))
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