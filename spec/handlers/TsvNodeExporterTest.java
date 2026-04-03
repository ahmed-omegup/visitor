package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.TsvNodeExporter;
import port.IFactory;

class TsvNodeExporterTest {
    private final IFactory factory = new Factory();
    @Test
    void exportsNodesAsTsvRows() {
        assertEquals(
            "path\ttype\tdetail\n"
                + "0\tAddition\t\n"
                + "0.0\tVariableReference\tx\n"
                + "0.1\tLiteral\t2\n",
factory.addition(factory.variableReference("x"), factory.literal("2")).accept(v.tsvNodeExporter())
        );
    }

    @Test
    void exportsTraversalExpressionIncludingFunctionCallRows() {
        var tsv =sampleTraversalExpression().accept(v.tsvNodeExporter());

        assertTrue(tsv.contains("0\tConditional\t\n"));
        assertTrue(tsv.contains("0.2\tFunctionCall\t7\n"));
        assertTrue(tsv.contains("0.2.7\tNegation\t\n"));
        assertTrue(tsv.contains("0.2.7.0\tLiteral\t4\n"));
    }
}