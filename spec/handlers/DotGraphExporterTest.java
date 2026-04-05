package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.visitors.DotGraphExporter;

abstract class DotGraphExporterTestBase<E> extends TestBase<E> {
    DotGraphExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsTraversalExpressionAsGraphvizGraph() {
        var graph =testSupport.v.dotGraphExporter().apply(testSupport.sampleTraversalExpression());

        assertTrue(graph.startsWith("digraph Expression {\n"));
        for (var label : new String[] {
            "Conditional", "Conjunction", "LessThan", "VariableReference(x)", "Literal(10)",
            "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
            "Division", "Modulo", "FunctionCall", "VariableReference(f)", "Exponentiation",
            "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"
        }) {
            assertTrue(graph.contains("label=\"" + label + "\""), "missing label " + label);
        }
    }

    @Test
    void reusesExistingNodeIdsForSharedSubexpressions() {
        var shared = factory.literal("a\"b");
        var graph =testSupport.v.dotGraphExporter().apply(factory.addition(shared, shared));

        assertEquals(1, occurrences(graph, "n1 [label=\"Literal(a\\\"b)\"]"));
        assertEquals(2, occurrences(graph, "n0 -> n1;"));
    }

    private int occurrences(String text, String needle) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(needle, index)) >= 0) {
            count++;
            index += needle.length();
        }
        return count;
    }
}

class DotGraphExporterTest extends DotGraphExporterTestBase<Expression> {
    DotGraphExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
