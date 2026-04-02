package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.PostOrderLabelPrinter;

class PostOrderLabelPrinterTest {
    @Test
    void printsLabelsInPostOrder() {
        assertEquals(
            "VariableReference(x) -> Literal(2) -> Addition",
            new PostOrderLabelPrinter().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("2")))
        );
    }

    @Test
    void printsTraversalExpressionInPostOrder() {
        var labels = new PostOrderLabelPrinter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(labels.startsWith("VariableReference(x) -> Literal(10) -> LessThan"));
        assertTrue(labels.contains("Literal(4) -> Negation -> FunctionCall"));
        assertTrue(labels.endsWith("Conditional"));
    }

    @Test
    void printsTraversalExpressionPostOrder() {
        var labels = new PostOrderLabelPrinter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(labels.startsWith("VariableReference(x) -> Literal(10)"));
        assertTrue(labels.contains("Literal(4) -> Negation"));
        assertTrue(labels.endsWith("Conditional"));
    }
}