package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.DepthAnnotatedPreorderPrinter;

class DepthAnnotatedPreorderPrinterTest {
    @Test
    void printsPreorderNodesWithDepth() {
        assertEquals(
            String.join("\n", "0: Addition", "1: VariableReference(x)", "1: Literal(2)"),
            new DepthAnnotatedPreorderPrinter().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("2")))
        );
    }

    @Test
    void printsTraversalExpressionAcrossAllVisitorBranches() {
        var rendered = new DepthAnnotatedPreorderPrinter().handle(TestSupport.sampleTraversalExpression());
        var lines = rendered.lines().toList();

        assertEquals(42, lines.size());
        assertEquals("0: Conditional", lines.get(0));
        assertTrue(lines.contains("1: Conjunction"));
        assertTrue(lines.contains("2: LessThan"));
        assertTrue(lines.contains("2: LogicalNot"));
        assertTrue(lines.contains("1: Addition"));
        assertTrue(lines.contains("2: Subtraction"));
        assertTrue(lines.contains("2: Multiplication"));
        assertTrue(lines.contains("3: Division"));
        assertTrue(lines.contains("3: Modulo"));
        assertTrue(lines.contains("1: FunctionCall"));
        assertTrue(lines.contains("2: Exponentiation"));
        assertTrue(lines.contains("2: Inequality"));
        assertTrue(lines.contains("2: GreaterThan"));
        assertTrue(lines.contains("2: LessThanOrEqual"));
        assertTrue(lines.contains("2: GreaterThanOrEqual"));
        assertTrue(lines.contains("2: Disjunction"));
        assertTrue(lines.contains("2: Negation"));
        assertEquals("3: Literal(4)", lines.get(lines.size() - 1));
    }
}