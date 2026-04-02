package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.YamlExpressionExporter;

class YamlExpressionExporterTest {
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
            new YamlExpressionExporter().handle(new FunctionCall(new VariableReference("sum"), new Literal("1"), new Literal("2")))
        );
    }

    @Test
    void exportsTraversalExpressionIncludingCompositeBranches() {
        var yaml = new YamlExpressionExporter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(yaml.contains("type: Conditional\n"));
        assertTrue(yaml.contains("type: GreaterThanOrEqual\n"));
        assertTrue(yaml.contains("type: FunctionCall\n"));
        assertTrue(yaml.contains("type: Negation\n"));
    }
}