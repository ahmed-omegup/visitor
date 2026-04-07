package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.CsvNodeExporter;
import lib.legacy.HandlerFactory;

abstract class CsvNodeExporterTestBase<E> extends TestBase<E> {
    CsvNodeExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsNodesAsCsvRows() {
        assertEquals(
            "path,type,detail\n"
                + "\"0\",\"FunctionCall\",\"2\"\n"
                + "\"0.0\",\"VariableReference\",\"sum\"\n"
                + "\"0.1\",\"Literal\",\"1\"\n"
                + "\"0.2\",\"Literal\",\"2\"\n",testSupport.v.csvNodeExporter().apply(factory.functionCall(factory.variableReference("sum"), of( factory.literal("1"), factory.literal("2"))))
        );
    }

    @Test
    void exportsTraversalExpressionIncludingAllCompositeRows() {
        var csv =testSupport.v.csvNodeExporter().apply(testSupport.sampleTraversalExpression());

        assertTrue(csv.contains("\"0\",\"Conditional\",\"\"\n"));
        assertTrue(csv.contains("\"0.1.1\",\"Multiplication\",\"\"\n"));
        assertTrue(csv.contains("\"0.2\",\"FunctionCall\",\"7\"\n"));
        assertTrue(csv.contains("\"0.2.7.0\",\"Literal\",\"4\"\n"));
    }
}

class CsvNodeExporterTest extends CsvNodeExporterTestBase<Expression> {
    CsvNodeExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
