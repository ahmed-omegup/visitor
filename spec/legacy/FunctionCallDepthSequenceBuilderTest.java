package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.FunctionCallDepthSequenceBuilder;
import lib.legacy.HandlerFactory;

abstract class FunctionCallDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    FunctionCallDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsFunctionCallDepthsInEncounterOrder() {
        assertEquals(of(1),testSupport.v.functionCallDepthSequenceBuilder().apply(testSupport.sampleTraversalExpression()));
        assertEquals(
            of(0, 1),testSupport.v.functionCallDepthSequenceBuilder().apply(factory.functionCall(factory.functionCall(factory.variableReference("f"), of()), of(factory.literal("1"))))
        );
    }
}

class FunctionCallDepthSequenceBuilderTest extends FunctionCallDepthSequenceBuilderTestBase<Expression> {
    FunctionCallDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
