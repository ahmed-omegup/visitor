package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.LevelGroupedLabelCollector;

class LevelGroupedLabelCollectorTest {
    @Test
    void groupsEncounteredLabelsByDepth() {
        var expected = new LinkedHashMap<Integer, List<String>>();
        expected.put(0, List.of("Addition"));
        expected.put(1, List.of("VariableReference", "Negation"));
        expected.put(2, List.of("Literal"));

        assertEquals(
            expected,
            new LevelGroupedLabelCollector().handle(new Addition(new VariableReference("x"), new Negation(new Literal("2"))))
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