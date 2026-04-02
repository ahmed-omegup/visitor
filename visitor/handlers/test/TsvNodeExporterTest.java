package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.TsvNodeExporter;

class TsvNodeExporterTest {
    @Test
    void exportsNodesAsTsvRows() {
        assertEquals(
            "path\ttype\tdetail\n"
                + "0\tAddition\t\n"
                + "0.0\tVariableReference\tx\n"
                + "0.1\tLiteral\t2\n",
            new TsvNodeExporter().handle(new Addition(new VariableReference("x"), new Literal("2")))
        );
    }

    @Test
    void exportsTraversalExpressionIncludingFunctionCallRows() {
        var tsv = new TsvNodeExporter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(tsv.contains("0\tConditional\t\n"));
        assertTrue(tsv.contains("0.2\tFunctionCall\t7\n"));
        assertTrue(tsv.contains("0.2.7\tNegation\t\n"));
        assertTrue(tsv.contains("0.2.7.0\tLiteral\t4\n"));
    }
}