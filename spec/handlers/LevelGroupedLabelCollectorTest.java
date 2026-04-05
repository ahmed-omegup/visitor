package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.LevelGroupedLabelCollector;

abstract class LevelGroupedLabelCollectorTestBase<E> extends TestBase<E> {
    LevelGroupedLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void groupsEncounteredLabelsByDepth() {
        var expected = new LinkedHashMap<Integer, List<String>>();
        expected.put(0, of("Addition"));
        expected.put(1, of("VariableReference", "Negation"));
        expected.put(2, of("Literal"));

        assertEquals(
            expected,testSupport.v.levelGroupedLabelCollector().apply(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void groupsTraversalExpressionLabelsByLevel() {
        var grouped =testSupport.v.levelGroupedLabelCollector().apply(testSupport.sampleTraversalExpression());

        assertEquals(of("Conditional"), grouped.get(0));
        assertEquals(of("Conjunction", "Addition", "FunctionCall"), grouped.get(1));
        assertEquals(12, grouped.get(2).size());
        assertEquals(20, grouped.get(3).size());
        assertEquals(6, grouped.get(4).size());
    }
}

class LevelGroupedLabelCollectorTest extends LevelGroupedLabelCollectorTestBase<Expression> {
    LevelGroupedLabelCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
