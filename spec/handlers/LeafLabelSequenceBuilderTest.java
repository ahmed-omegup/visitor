package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.LeafLabelSequenceBuilder;

abstract class LeafLabelSequenceBuilderTestBase<E extends Expression> extends TestBase<E> {
    LeafLabelSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void recordsLeafLabelsInEncounterOrder() {
        assertEquals(
            List.of(
                "variable:x", "literal:10", "literal:1", "literal:0", "literal:7", "literal:2",
                "literal:8", "literal:2", "literal:9", "literal:4", "variable:f", "literal:2",
                "literal:3", "literal:5", "literal:6", "literal:7", "literal:1", "literal:2",
                "literal:2", "literal:3", "literal:3", "literal:0", "literal:1", "literal:4"
            ),
testSupport.sampleTraversalExpression().accept(testSupport.v.leafLabelSequenceBuilder())
        );
    }
}

class LeafLabelSequenceBuilderTest extends LeafLabelSequenceBuilderTestBase<Expression> {
    LeafLabelSequenceBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
