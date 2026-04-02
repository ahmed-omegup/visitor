package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.ArityHistogramBuilder;

class ArityHistogramBuilderTest {
    @Test
    void countsFunctionCallsByArity() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(0, 1);
        expected.put(2, 1);

        assertEquals(
            expected,
            new ArityHistogramBuilder().handle(
                new Addition(
                    new FunctionCall(new VariableReference("ping")),
                    new FunctionCall(new VariableReference("sum"), new Literal("1"), new Literal("2"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionFunctionArity() {
        assertEquals(Map.of(7, 1), new ArityHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}