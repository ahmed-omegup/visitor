package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionCallDepthSequenceBuilder;

abstract class FunctionCallDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    FunctionCallDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsFunctionCallDepthsInEncounterOrder() {
        assertEquals(of(1),testSupport.sampleTraversalExpression().accept(testSupport.v.functionCallDepthSequenceBuilder()));
        assertEquals(
            of(0, 1),
factory.functionCall(factory.functionCall(factory.variableReference("f"), of()), of(factory.literal("1"))).accept(testSupport.v.functionCallDepthSequenceBuilder())
        );
    }
}

class FunctionCallDepthSequenceBuilderTest extends FunctionCallDepthSequenceBuilderTestBase<Expression> {
    FunctionCallDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
