package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.handlers.TreeDiagramPrinter;
import port.IFactory;

class TreeDiagramPrinterTest {
    private final IFactory factory = new Factory();
    @Test
    void rendersZeroArgumentFunctionCallTree() {
        assertEquals(
            "└── FunctionCall\n"
                + "    └── VariableReference(ping)\n",
factory.functionCall(factory.variableReference("ping")).accept(TestSupport.handlers().treeDiagramPrinter())
        );
    }

    @Test
    void rendersAllExpressionKinds() {
        var tree =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().treeDiagramPrinter());

        assertTrue(tree.contains("└── Conditional\n"));
        assertTrue(tree.contains("├── LessThanOrEqual\n") || tree.contains("└── LessThanOrEqual\n"));
        assertTrue(tree.contains("├── GreaterThanOrEqual\n") || tree.contains("└── GreaterThanOrEqual\n"));
    }
}