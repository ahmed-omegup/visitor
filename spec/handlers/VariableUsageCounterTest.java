package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.visitors.VariableUsageCounter;
import port.IFactory;

class VariableUsageCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsVariableUsagesInEncounterOrder() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("x", 2);
        expected.put("sum", 1);

        assertEquals(
            expected,
factory.addition(
                    factory.variableReference("x"),
                    factory.functionCall(factory.variableReference("sum"), factory.variableReference("x"))
                ).accept(v.variableUsageCounter())
        );
    }

    @Test
    void countsTraversalExpressionVariablesIncludingFunctionCallee() {
        assertEquals(Map.of("x", 1, "f", 1),sampleTraversalExpression().accept(v.variableUsageCounter()));
    }
}