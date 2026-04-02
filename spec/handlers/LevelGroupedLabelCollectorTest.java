package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.LevelGroupedLabelCollector;

class LevelGroupedLabelCollectorTest {
    @Test
    void groupsEncounteredLabelsByDepth() {
        var expected = new LinkedHashMap<Integer, List<String>>();
        expected.put(0, List.of("Addition"));
        expected.put(1, List.of("VariableReference", "Negation"));
        expected.put(2, List.of("Literal"));

        assertEquals(
            expected,
            new LevelGroupedLabelCollector().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.negation(lib.expression.Expression.literal("2"))))
        );
    }

    @Test
    void groupsTraversalExpressionLabelsByLevel() {
        var grouped = new LevelGroupedLabelCollector().handle(TestSupport.sampleTraversalExpression());

        assertEquals(List.of("Conditional"), grouped.get(0));
        assertEquals(List.of("Conjunction", "Addition", "FunctionCall"), grouped.get(1));
        assertEquals(12, grouped.get(2).size());
        assertEquals(20, grouped.get(3).size());
        assertEquals(6, grouped.get(4).size());
    }
}