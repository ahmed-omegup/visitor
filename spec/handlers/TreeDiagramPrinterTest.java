package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.handlers.TreeDiagramPrinter;

class TreeDiagramPrinterTest {
    @Test
    void rendersZeroArgumentFunctionCallTree() {
        assertEquals(
            "└── FunctionCall\n"
                + "    └── VariableReference(ping)\n",
            new TreeDiagramPrinter().handle(functionCall(variableReference("ping")))
        );
    }

    @Test
    void rendersAllExpressionKinds() {
        var tree = new TreeDiagramPrinter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(tree.contains("└── Conditional\n"));
        assertTrue(tree.contains("├── LessThanOrEqual\n") || tree.contains("└── LessThanOrEqual\n"));
        assertTrue(tree.contains("├── GreaterThanOrEqual\n") || tree.contains("└── GreaterThanOrEqual\n"));
    }
}