package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.LeafDepthSequenceBuilder;
import port.IFactory;

abstract class LeafDepthSequenceBuilderTestBase<E extends Expression> extends TestBase<E> {
    LeafDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsLeafDepthsInEncounterOrder() {
        assertEquals(
            of(1, 2),
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(testSupport.v.leafDepthSequenceBuilder())
        );
    }

    @Test
    void recordsTraversalExpressionLeafDepths() {
        assertEquals(
            of(3, 3, 4, 4, 3, 3, 4, 4, 4, 4, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3),
testSupport.sampleTraversalExpression().accept(testSupport.v.leafDepthSequenceBuilder())
        );
    }
}

class LeafDepthSequenceBuilderTest extends LeafDepthSequenceBuilderTestBase<Expression> {
    LeafDepthSequenceBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
