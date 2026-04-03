package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.LeafLabelSequenceBuilder;

class LeafLabelSequenceBuilderTest {
    @Test
    void recordsLeafLabelsInEncounterOrder() {
        assertEquals(
            List.of(
                "variable:x", "literal:10", "literal:1", "literal:0", "literal:7", "literal:2",
                "literal:8", "literal:2", "literal:9", "literal:4", "variable:f", "literal:2",
                "literal:3", "literal:5", "literal:6", "literal:7", "literal:1", "literal:2",
                "literal:2", "literal:3", "literal:3", "literal:0", "literal:1", "literal:4"
            ),
sampleTraversalExpression().accept(v.leafLabelSequenceBuilder())
        );
    }
}