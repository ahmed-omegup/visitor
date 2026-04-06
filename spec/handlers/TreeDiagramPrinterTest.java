package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.handlers.TreeDiagramPrinter;

abstract class TreeDiagramPrinterTestBase<E> extends TestBase<E> {
    TreeDiagramPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void rendersZeroArgumentFunctionCallTree() {
        assertEquals(
            "└── FunctionCall\n"
                + "    └── VariableReference(ping)\n",testSupport.v.treeDiagramPrinter().apply(factory.functionCall(factory.variableReference("ping"), of()))
        );
    }

    @Test
    void rendersAllExpressionKinds() {
        var tree =testSupport.v.treeDiagramPrinter().apply(testSupport.sampleTraversalExpression());

        assertTrue(tree.contains("└── Conditional\n"));
        assertTrue(tree.contains("├── LessThanOrEqual\n") || tree.contains("└── LessThanOrEqual\n"));
        assertTrue(tree.contains("├── GreaterThanOrEqual\n") || tree.contains("└── GreaterThanOrEqual\n"));
    }
}

class TreeDiagramPrinterTest extends TreeDiagramPrinterTestBase<Expression> {
    TreeDiagramPrinterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
