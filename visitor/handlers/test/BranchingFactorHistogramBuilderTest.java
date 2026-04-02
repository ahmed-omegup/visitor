package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.BranchingFactorHistogramBuilder;

class BranchingFactorHistogramBuilderTest {
    @Test
    void countsNodesByBranchingFactor() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 1);
        expected.put(0, 4);
        expected.put(3, 1);

        assertEquals(
            expected,
            new BranchingFactorHistogramBuilder().handle(
                new Addition(new VariableReference("x"), new FunctionCall(new VariableReference("f"), new Literal("1"), new Literal("2")))
            )
        );
    }

    @Test
    void countsTraversalExpressionBranchingFactors() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 1);
        expected.put(2, 14);
        expected.put(0, 24);
        expected.put(1, 2);
        expected.put(8, 1);

        assertEquals(expected, new BranchingFactorHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}