package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.YamlExpressionExporter;
import port.IFactory;

class YamlExpressionExporterTest {
    private final IFactory factory = new Factory();
    @Test
    void exportsFunctionCallAsYamlTree() {
        assertEquals(
            "type: FunctionCall\n"
                + "children:\n"
                + "  -\n"
                + "    type: VariableReference\n"
                + "    name: \"sum\"\n"
                + "  -\n"
                + "    type: Literal\n"
                + "    value: \"1\"\n"
                + "  -\n"
                + "    type: Literal\n"
                + "    value: \"2\"\n",
factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2")).accept(TestSupport.handlers().yamlExpressionExporter())
        );
    }

    @Test
    void exportsTraversalExpressionIncludingCompositeBranches() {
        var yaml =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().yamlExpressionExporter());

        assertTrue(yaml.contains("type: Conditional\n"));
        assertTrue(yaml.contains("type: GreaterThanOrEqual\n"));
        assertTrue(yaml.contains("type: FunctionCall\n"));
        assertTrue(yaml.contains("type: Negation\n"));
    }
}