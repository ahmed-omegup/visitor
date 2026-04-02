package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.CsvNodeExporter;

class CsvNodeExporterTest {
    private final Factory factory = new Factory();
    @Test
    void exportsNodesAsCsvRows() {
        assertEquals(
            "path,type,detail\n"
                + "\"0\",\"FunctionCall\",\"2\"\n"
                + "\"0.0\",\"VariableReference\",\"sum\"\n"
                + "\"0.1\",\"Literal\",\"1\"\n"
                + "\"0.2\",\"Literal\",\"2\"\n",
            new CsvNodeExporter().handle(factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2")))
        );
    }

    @Test
    void exportsTraversalExpressionIncludingAllCompositeRows() {
        var csv = new CsvNodeExporter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(csv.contains("\"0\",\"Conditional\",\"\"\n"));
        assertTrue(csv.contains("\"0.1.1\",\"Multiplication\",\"\"\n"));
        assertTrue(csv.contains("\"0.2\",\"FunctionCall\",\"7\"\n"));
        assertTrue(csv.contains("\"0.2.7.0\",\"Literal\",\"4\"\n"));
    }
}