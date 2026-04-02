package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.RootToLeafTracePrinter;

class RootToLeafTracePrinterTest {
    @Test
    void printsOneTracePerLeaf() {
        assertEquals(
            String.join("\n", "Addition -> VariableReference(x)", "Addition -> Negation -> Literal(2)"),
            new RootToLeafTracePrinter().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.negation(lib.expression.Expression.literal("2"))))
        );
    }

    @Test
    void printsTraversalExpressionRootToLeafTraces() {
        var traces = new RootToLeafTracePrinter().handle(TestSupport.sampleTraversalExpression()).lines().toList();

        assertEquals(24, traces.size());
        assertEquals("Conditional -> Conjunction -> LessThan -> VariableReference(x)", traces.get(0));
        assertTrue(traces.contains("Conditional -> Conjunction -> LogicalNot -> Equality -> Literal(0)"));
        assertTrue(traces.contains("Conditional -> Addition -> Multiplication -> Division -> Literal(8)"));
        assertTrue(traces.contains("Conditional -> FunctionCall -> VariableReference(f)"));
        assertEquals("Conditional -> FunctionCall -> Negation -> Literal(4)", traces.get(traces.size() - 1));
    }
}