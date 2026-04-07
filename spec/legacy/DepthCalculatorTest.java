package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.legacy.DepthCalculator;
import lib.legacy.HandlerFactory;

abstract class DepthCalculatorTestBase<E> extends TestBase<E> {
    DepthCalculatorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void computesDepthForTraversalExpression() {
        assertEquals(5,testSupport.v.depthCalculator().apply(testSupport.sampleTraversalExpression()));
    }

    @Test
    void countsZeroArgumentFunctionCallDepth() {
        assertEquals(2,testSupport.v.depthCalculator().apply(factory.functionCall(factory.variableReference("ping"), of())));
    }
}

class DepthCalculatorTest extends DepthCalculatorTestBase<Expression> {
    DepthCalculatorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
