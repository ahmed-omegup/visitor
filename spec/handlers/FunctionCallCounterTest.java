package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionCallCounter;
import port.IFactory;

abstract class FunctionCallCounterTestBase<E extends Expression> extends TestBase<E> {
    FunctionCallCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsNestedFunctionCalls() {
        assertEquals(
            2,
factory.addition(
                    factory.functionCall(factory.variableReference("left"), factory.literal("1")),
                    factory.functionCall(factory.variableReference("right"), factory.literal("2"), factory.literal("3"))
                ).accept(testSupport.v.functionCallCounter())
        );
    }

    @Test
    void countsTraversalExpressionFunctionCall() {
        assertEquals(1,testSupport.sampleTraversalExpression().accept(testSupport.v.functionCallCounter()));
    }
}

class FunctionCallCounterTest extends FunctionCallCounterTestBase<Expression> {
    FunctionCallCounterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
