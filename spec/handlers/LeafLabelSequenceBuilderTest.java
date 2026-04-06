package spec.handlers;

import lib.expression.Expression;
import lib.handlers.HandlerFactory;
import lib.handlers.LeafLabelSequenceBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

abstract class LeafLabelSequenceBuilderTestBase<E> extends TestBase<E> {
    LeafLabelSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void recordsLeafLabelsInEncounterOrder() {
        assertEquals(
            of(
                "variable:x", "literal:10", "literal:1", "literal:0", "literal:7", "literal:2",
                "literal:8", "literal:2", "literal:9", "literal:4", "variable:f", "literal:2",
                "literal:3", "literal:5", "literal:6", "literal:7", "literal:1", "literal:2",
                "literal:2", "literal:3", "literal:3", "literal:0", "literal:1", "literal:4"
            ),testSupport.v.leafLabelSequenceBuilder().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class LeafLabelSequenceBuilderTest extends LeafLabelSequenceBuilderTestBase<Expression> {
    LeafLabelSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
