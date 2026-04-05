package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.NodeHistogramBuilder;

abstract class NodeHistogramBuilderTestBase<E> extends TestBase<E> {
    NodeHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void buildsHistogramForTraversalExpression() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("Conditional", 1);
        expected.put("Conjunction", 1);
        expected.put("LessThan", 1);
        expected.put("VariableReference", 2);
        expected.put("Literal", 22);
        expected.put("LogicalNot", 1);
        expected.put("Equality", 1);
        expected.put("Addition", 1);
        expected.put("Subtraction", 1);
        expected.put("Multiplication", 1);
        expected.put("Division", 1);
        expected.put("Modulo", 1);
        expected.put("FunctionCall", 1);
        expected.put("Exponentiation", 1);
        expected.put("Inequality", 1);
        expected.put("GreaterThan", 1);
        expected.put("LessThanOrEqual", 1);
        expected.put("GreaterThanOrEqual", 1);
        expected.put("Disjunction", 1);
        expected.put("Negation", 1);

        assertEquals(expected,testSupport.v.nodeHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class NodeHistogramBuilderTest extends NodeHistogramBuilderTestBase<Expression> {
    NodeHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
