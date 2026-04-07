package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.BranchingFactorHistogramBuilder;
import lib.legacy.HandlerFactory;

abstract class BranchingFactorHistogramBuilderTestBase<E> extends TestBase<E> {
    BranchingFactorHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsNodesByBranchingFactor() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 1);
        expected.put(0, 4);
        expected.put(3, 1);

        assertEquals(
            expected,testSupport.v.branchingFactorHistogramBuilder().apply(factory.addition(factory.variableReference("x"), factory.functionCall(factory.variableReference("f"), of( factory.literal("1"), factory.literal("2")))))
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

        assertEquals(expected,testSupport.v.branchingFactorHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class BranchingFactorHistogramBuilderTest extends BranchingFactorHistogramBuilderTestBase<Expression> {
    BranchingFactorHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
