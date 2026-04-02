package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.ArityHistogramBuilder;

class ArityHistogramBuilderTest {
    @Test
    void countsFunctionCallsByArity() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(0, 1);
        expected.put(2, 1);

        assertEquals(
            expected,
            new ArityHistogramBuilder().handle(
                lib.expression.Expression.addition(
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("ping")),
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionFunctionArity() {
        assertEquals(Map.of(7, 1), new ArityHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}