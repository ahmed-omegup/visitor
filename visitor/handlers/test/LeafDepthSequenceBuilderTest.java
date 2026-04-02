package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.LeafDepthSequenceBuilder;

class LeafDepthSequenceBuilderTest {
    @Test
    void recordsLeafDepthsInEncounterOrder() {
        assertEquals(
            List.of(1, 2),
            new LeafDepthSequenceBuilder().handle(new Addition(new VariableReference("x"), new Negation(new Literal("2"))))
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