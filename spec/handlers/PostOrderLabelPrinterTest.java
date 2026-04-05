package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.PostOrderLabelPrinter;

abstract class PostOrderLabelPrinterTestBase<E> extends TestBase<E> {
    PostOrderLabelPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void printsLabelsInPostOrder() {
        assertEquals(
            "VariableReference(x) -> Literal(2) -> Addition",testSupport.v.postOrderLabelPrinter().apply(factory.addition(factory.variableReference("x"), factory.literal("2")))
        );
    }

    @Test
    void printsTraversalExpressionInPostOrder() {
        var labels =testSupport.v.postOrderLabelPrinter().apply(testSupport.sampleTraversalExpression());

        assertTrue(labels.startsWith("VariableReference(x) -> Literal(10) -> LessThan"));
        assertTrue(labels.contains("Literal(4) -> Negation -> FunctionCall"));
        assertTrue(labels.endsWith("Conditional"));
    }

    @Test
    void printsTraversalExpressionPostOrder() {
        var labels =testSupport.v.postOrderLabelPrinter().apply(testSupport.sampleTraversalExpression());

        assertTrue(labels.startsWith("VariableReference(x) -> Literal(10)"));
        assertTrue(labels.contains("Literal(4) -> Negation"));
        assertTrue(labels.endsWith("Conditional"));
    }
}

class PostOrderLabelPrinterTest extends PostOrderLabelPrinterTestBase<Expression> {
    PostOrderLabelPrinterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
