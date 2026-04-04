package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.visitors.TreeDiagramPrinter;
import port.IFactory;

abstract class TreeDiagramPrinterTestBase<E extends Expression> extends TestBase<E> {
    TreeDiagramPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void rendersZeroArgumentFunctionCallTree() {
        assertEquals(
            "└── FunctionCall\n"
                + "    └── VariableReference(ping)\n",
factory.functionCall(factory.variableReference("ping"), java.util.List.of()).accept(testSupport.v.treeDiagramPrinter())
        );
    }

    @Test
    void rendersAllExpressionKinds() {
        var tree =testSupport.sampleTraversalExpression().accept(testSupport.v.treeDiagramPrinter());

        assertTrue(tree.contains("└── Conditional\n"));
        assertTrue(tree.contains("├── LessThanOrEqual\n") || tree.contains("└── LessThanOrEqual\n"));
        assertTrue(tree.contains("├── GreaterThanOrEqual\n") || tree.contains("└── GreaterThanOrEqual\n"));
    }
}

class TreeDiagramPrinterTest extends TreeDiagramPrinterTestBase<Expression> {
    TreeDiagramPrinterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
