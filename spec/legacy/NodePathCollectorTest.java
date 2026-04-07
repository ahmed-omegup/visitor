package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.NodePathCollector;

abstract class NodePathCollectorTestBase<E> extends TestBase<E> {
    NodePathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsTypedPaths() {
        assertEquals(
            of("0:Addition", "0.0:VariableReference", "0.1:Literal"),testSupport.v.nodePathCollector().apply(factory.addition(factory.variableReference("x"), factory.literal("2")))
        );
    }

    @Test
    void collectsPathsForTraversalExpressionKinds() {
        var paths =testSupport.v.nodePathCollector().apply(testSupport.sampleTraversalExpression());

        assertTrue(paths.contains("0:Conditional"));
        assertTrue(paths.contains("0.1.1:Multiplication"));
        assertTrue(paths.contains("0.2:FunctionCall"));
        assertTrue(paths.contains("0.2.7.0:Literal"));
    }
}

class NodePathCollectorTest extends NodePathCollectorTestBase<Expression> {
    NodePathCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
