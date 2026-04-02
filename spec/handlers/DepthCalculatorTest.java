package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.handlers.DepthCalculator;

class DepthCalculatorTest {
    @Test
    void computesDepthForTraversalExpression() {
        assertEquals(5, new DepthCalculator().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsZeroArgumentFunctionCallDepth() {
        assertEquals(2, new DepthCalculator().handle(functionCall(variableReference("ping"))));
    }
}