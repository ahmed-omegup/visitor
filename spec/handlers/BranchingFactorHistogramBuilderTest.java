package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.BranchingFactorHistogramBuilder;
import port.IFactory;

abstract class BranchingFactorHistogramBuilderTestBase<E extends Expression> extends TestBase<E> {
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
            expected,
factory.addition(factory.variableReference("x"), factory.functionCall(factory.variableReference("f"), factory.literal("1"), factory.literal("2"))).accept(testSupport.v.branchingFactorHistogramBuilder())
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

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.branchingFactorHistogramBuilder()));
    }
}

class BranchingFactorHistogramBuilderTest extends BranchingFactorHistogramBuilderTestBase<Expression> {
    BranchingFactorHistogramBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
