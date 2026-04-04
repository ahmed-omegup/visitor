package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.YamlExpressionExporter;

abstract class YamlExpressionExporterTestBase<E extends Expression> extends TestBase<E> {
    YamlExpressionExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


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
factory.functionCall(factory.variableReference("sum"), of( factory.literal("1"), factory.literal("2"))).accept(testSupport.v.yamlExpressionExporter())
        );
    }

    @Test
    void exportsTraversalExpressionIncludingCompositeBranches() {
        var yaml =testSupport.sampleTraversalExpression().accept(testSupport.v.yamlExpressionExporter());

        assertTrue(yaml.contains("type: Conditional\n"));
        assertTrue(yaml.contains("type: GreaterThanOrEqual\n"));
        assertTrue(yaml.contains("type: FunctionCall\n"));
        assertTrue(yaml.contains("type: Negation\n"));
    }
}

class YamlExpressionExporterTest extends YamlExpressionExporterTestBase<Expression> {
    YamlExpressionExporterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
