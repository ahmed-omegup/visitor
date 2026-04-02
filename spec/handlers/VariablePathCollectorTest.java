package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.handlers.VariablePathCollector;

class VariablePathCollectorTest {
    @Test
    void groupsVariablePathsByName() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("x", List.of("root.left", "root.right.arguments[0]"));
        expected.put("f", List.of("root.right.callee"));

        assertEquals(
            expected,
            new VariablePathCollector().handle(
                addition(variableReference("x"), functionCall(variableReference("f"), variableReference("x")))
            )
        );
    }

    @Test
    void groupsTraversalExpressionVariablePaths() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("x", List.of("root.condition.left.left"));
        expected.put("f", List.of("root.whenFalse.callee"));

        assertEquals(expected, new VariablePathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}