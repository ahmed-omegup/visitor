package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionCallCounter;
import lib.handlers.HandlerFactory;

abstract class FunctionCallCounterTestBase<E> extends TestBase<E> {
    FunctionCallCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsNestedFunctionCalls() {
        assertEquals(
            2,testSupport.v.functionCallCounter().apply(factory.addition(
                    factory.functionCall(factory.variableReference("left"), of( factory.literal("1"))),
                    factory.functionCall(factory.variableReference("right"), of( factory.literal("2"), factory.literal("3")))
                ))
        );
    }

    @Test
    void countsTraversalExpressionFunctionCall() {
        assertEquals(1,testSupport.v.functionCallCounter().apply(testSupport.sampleTraversalExpression()));
    }
}

class FunctionCallCounterTest extends FunctionCallCounterTestBase<Expression> {
    FunctionCallCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
