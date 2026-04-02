package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.handlers.VariableUsageCounter;

class VariableUsageCounterTest {
    @Test
    void countsVariableUsagesInEncounterOrder() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("x", 2);
        expected.put("sum", 1);

        assertEquals(
            expected,
            new VariableUsageCounter().handle(
                addition(
                    variableReference("x"),
                    functionCall(variableReference("sum"), variableReference("x"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionVariablesIncludingFunctionCallee() {
        assertEquals(Map.of("x", 1, "f", 1), new VariableUsageCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}