package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.FunctionCall;
import visitor.expression.VariableReference;
import visitor.handlers.DepthCalculator;

class DepthCalculatorTest {
    @Test
    void computesDepthForTraversalExpression() {
        assertEquals(5, new DepthCalculator().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsZeroArgumentFunctionCallDepth() {
        assertEquals(2, new DepthCalculator().handle(new FunctionCall(new VariableReference("ping"))));
    }
}