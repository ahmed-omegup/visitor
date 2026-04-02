package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.NodePathCollector;
import port.IFactory;

class NodePathCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void collectsTypedPaths() {
        assertEquals(
            List.of("0:Addition", "0.0:VariableReference", "0.1:Literal"),
            new NodePathCollector().handle(factory.addition(factory.variableReference("x"), factory.literal("2")))
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