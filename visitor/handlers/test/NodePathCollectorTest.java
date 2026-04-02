package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.NodePathCollector;

class NodePathCollectorTest {
    @Test
    void collectsTypedPaths() {
        assertEquals(
            List.of("0:Addition", "0.0:VariableReference", "0.1:Literal"),
            new NodePathCollector().handle(new Addition(new VariableReference("x"), new Literal("2")))
        );
    }

    @Test
    void collectsPathsForTraversalExpressionKinds() {
        var paths = new NodePathCollector().handle(TestSupport.sampleTraversalExpression());

        assertTrue(paths.contains("0:Conditional"));
        assertTrue(paths.contains("0.1.1:Multiplication"));
        assertTrue(paths.contains("0.2:FunctionCall"));
        assertTrue(paths.contains("0.2.7.0:Literal"));
    }
}