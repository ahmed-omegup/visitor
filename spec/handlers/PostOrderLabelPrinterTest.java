package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.PostOrderLabelPrinter;
import port.IFactory;

class PostOrderLabelPrinterTest {
    private final IFactory factory = new Factory();
    @Test
    void printsLabelsInPostOrder() {
        assertEquals(
            "VariableReference(x) -> Literal(2) -> Addition",
factory.addition(factory.variableReference("x"), factory.literal("2")).accept(v.postOrderLabelPrinter())
        );
    }

    @Test
    void printsTraversalExpressionInPostOrder() {
        var labels =sampleTraversalExpression().accept(v.postOrderLabelPrinter());

        assertTrue(labels.startsWith("VariableReference(x) -> Literal(10) -> LessThan"));
        assertTrue(labels.contains("Literal(4) -> Negation -> FunctionCall"));
        assertTrue(labels.endsWith("Conditional"));
    }

    @Test
    void printsTraversalExpressionPostOrder() {
        var labels =sampleTraversalExpression().accept(v.postOrderLabelPrinter());

        assertTrue(labels.startsWith("VariableReference(x) -> Literal(10)"));
        assertTrue(labels.contains("Literal(4) -> Negation"));
        assertTrue(labels.endsWith("Conditional"));
    }
}