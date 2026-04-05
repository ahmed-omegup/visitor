package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.VariableDepthSequenceBuilder;

abstract class VariableDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    VariableDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void recordsVariableDepthsInEncounterOrder() {
        assertEquals(of(3, 2),testSupport.v.variableDepthSequenceBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class VariableDepthSequenceBuilderTest extends VariableDepthSequenceBuilderTestBase<Expression> {
    VariableDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
