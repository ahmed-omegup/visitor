package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.RootToLeafTracePrinter;

class RootToLeafTracePrinterTest {
    @Test
    void printsOneTracePerLeaf() {
        assertEquals(
            String.join("\n", "Addition -> VariableReference(x)", "Addition -> Negation -> Literal(2)"),
            new RootToLeafTracePrinter().handle(new Addition(new VariableReference("x"), new Negation(new Literal("2"))))
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