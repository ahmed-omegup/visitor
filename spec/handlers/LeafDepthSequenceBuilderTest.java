package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.LeafDepthSequenceBuilder;
import port.IFactory;

class LeafDepthSequenceBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void recordsLeafDepthsInEncounterOrder() {
        assertEquals(
            List.of(1, 2),
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(v.leafDepthSequenceBuilder())
        );
    }

    @Test
    void recordsTraversalExpressionLeafDepths() {
        assertEquals(
            List.of(3, 3, 4, 4, 3, 3, 4, 4, 4, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3),
sampleTraversalExpression().accept(v.leafDepthSequenceBuilder())
        );
    }
}