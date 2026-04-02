package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.BranchingFactorHistogramBuilder;

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
                lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("f"), lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")))
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