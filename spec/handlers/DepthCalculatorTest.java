package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.handlers.DepthCalculator;
import port.IFactory;

class DepthCalculatorTest {
    private final IFactory factory = new Factory();
    @Test
    void computesDepthForTraversalExpression() {
        assertEquals(5,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().depthCalculator()));
    }

    @Test
    void countsZeroArgumentFunctionCallDepth() {
        assertEquals(2,factory.functionCall(factory.variableReference("ping")).accept(TestSupport.handlers().depthCalculator()));
    }
}