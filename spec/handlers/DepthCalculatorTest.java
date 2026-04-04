package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.visitors.DepthCalculator;
import port.IFactory;

abstract class DepthCalculatorTestBase<E extends Expression> extends TestBase<E> {
    DepthCalculatorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void computesDepthForTraversalExpression() {
        assertEquals(5,testSupport.sampleTraversalExpression().accept(testSupport.v.depthCalculator()));
    }

    @Test
    void countsZeroArgumentFunctionCallDepth() {
        assertEquals(2,factory.functionCall(factory.variableReference("ping"), of()).accept(testSupport.v.depthCalculator()));
    }
}

class DepthCalculatorTest extends DepthCalculatorTestBase<Expression> {
    DepthCalculatorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
