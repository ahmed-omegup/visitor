package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.VariableReference;
import visitor.handlers.VariableUsageCounter;

class VariableUsageCounterTest {
    @Test
    void countsVariableUsagesInEncounterOrder() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("x", 2);
        expected.put("sum", 1);

        assertEquals(
            expected,
            new VariableUsageCounter().handle(
                new Addition(
                    new VariableReference("x"),
                    new FunctionCall(new VariableReference("sum"), new VariableReference("x"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionVariablesIncludingFunctionCallee() {
        assertEquals(Map.of("x", 1, "f", 1), new VariableUsageCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}