package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.LevelWidthHistogramBuilder;
import port.IFactory;

abstract class LevelWidthHistogramBuilderTestBase<E extends Expression> extends TestBase<E> {
    LevelWidthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsNodesPerTreeLevel() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(0, 1);
        expected.put(1, 2);
        expected.put(2, 1);

        assertEquals(
            expected,
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(testSupport.v.levelWidthHistogramBuilder())
        );
    }

    @Test
    void countsTraversalExpressionWidthsAcrossMultipleLevels() {
        assertEquals(Map.of(0, 1, 1, 3, 2, 12, 3, 20, 4, 6),testSupport.sampleTraversalExpression().accept(testSupport.v.levelWidthHistogramBuilder()));
    }

    @Test
    void countsZeroArgumentFunctionCallWithoutArgumentLoopIterations() {
        assertEquals(Map.of(0, 1, 1, 1),factory.functionCall(factory.variableReference("ping"), of()).accept(testSupport.v.levelWidthHistogramBuilder()));
    }
}

class LevelWidthHistogramBuilderTest extends LevelWidthHistogramBuilderTestBase<Expression> {
    LevelWidthHistogramBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
