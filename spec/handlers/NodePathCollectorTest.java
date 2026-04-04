package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.NodePathCollector;

abstract class NodePathCollectorTestBase<E> extends TestBase<E> {
    NodePathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsTypedPaths() {
        assertEquals(
            of("0:Addition", "0.0:VariableReference", "0.1:Literal"),
factory.addition(factory.variableReference("x"), factory.literal("2")).accept(testSupport.v.nodePathCollector())
        );
    }

    @Test
    void collectsPathsForTraversalExpressionKinds() {
        var paths =testSupport.sampleTraversalExpression().accept(testSupport.v.nodePathCollector());

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
