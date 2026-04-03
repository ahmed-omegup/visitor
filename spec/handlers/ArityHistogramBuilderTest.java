package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.ArityHistogramBuilder;
import port.IFactory;

class ArityHistogramBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void countsFunctionCallsByArity() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(0, 1);
        expected.put(2, 1);

        assertEquals(
            expected,
factory.addition(
                    factory.functionCall(factory.variableReference("ping")),
                    factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2"))
                ).accept(TestSupport.handlers().arityHistogramBuilder())
        );
    }

    @Test
    void countsTraversalExpressionFunctionArity() {
        assertEquals(Map.of(7, 1),TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().arityHistogramBuilder()));
    }
}