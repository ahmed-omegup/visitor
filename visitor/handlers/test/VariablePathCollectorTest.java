package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.VariableReference;
import visitor.handlers.VariablePathCollector;

class VariablePathCollectorTest {
    @Test
    void groupsVariablePathsByName() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("x", List.of("root.left", "root.right.arguments[0]"));
        expected.put("f", List.of("root.right.callee"));

        assertEquals(
            expected,
            new VariablePathCollector().handle(
                new Addition(new VariableReference("x"), new FunctionCall(new VariableReference("f"), new VariableReference("x")))
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