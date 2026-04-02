package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.LevelWidthHistogramBuilder;

class LevelWidthHistogramBuilderTest {
    @Test
    void countsNodesPerTreeLevel() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(0, 1);
        expected.put(1, 2);
        expected.put(2, 1);

        assertEquals(
            expected,
            new LevelWidthHistogramBuilder().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("2"))))
        );
    }

    @Test
    void countsTraversalExpressionWidthsAcrossMultipleLevels() {
        assertEquals(Map.of(0, 1, 1, 3, 2, 12, 3, 20, 4, 6), new LevelWidthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsZeroArgumentFunctionCallWithoutArgumentLoopIterations() {
        assertEquals(Map.of(0, 1, 1, 1), new LevelWidthHistogramBuilder().handle(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("ping"))));
    }
}