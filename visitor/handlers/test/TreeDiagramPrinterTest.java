package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.FunctionCall;
import visitor.expression.VariableReference;
import visitor.handlers.TreeDiagramPrinter;

class TreeDiagramPrinterTest {
    @Test
    void rendersZeroArgumentFunctionCallTree() {
        assertEquals(
            "└── FunctionCall\n"
                + "    └── VariableReference(ping)\n",
            new TreeDiagramPrinter().handle(new FunctionCall(new VariableReference("ping")))
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