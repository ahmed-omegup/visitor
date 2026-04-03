package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.visitors.DepthCalculator;
import port.IFactory;

class DepthCalculatorTest {
    private final IFactory factory = new Factory();
    @Test
    void computesDepthForTraversalExpression() {
        assertEquals(5,sampleTraversalExpression().accept(v.depthCalculator()));
    }

    @Test
    void countsZeroArgumentFunctionCallDepth() {
        assertEquals(2,factory.functionCall(factory.variableReference("ping")).accept(v.depthCalculator()));
    }
}