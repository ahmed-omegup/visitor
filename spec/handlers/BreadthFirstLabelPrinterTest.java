package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.BreadthFirstLabelPrinter;
import port.IFactory;

class BreadthFirstLabelPrinterTest {
    private final IFactory factory = new Factory();
    @Test
    void printsLabelsBreadthFirst() {
        assertEquals(
            "Addition | VariableReference(x) | Negation | Literal(2)",
            new BreadthFirstLabelPrinter().handle(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void printsTraversalExpressionBreadthFirst() {
        var labels = new BreadthFirstLabelPrinter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(labels.startsWith("Conditional | Conjunction | Addition | FunctionCall | LessThan"));
        assertTrue(labels.contains("GreaterThanOrEqual | Disjunction | Negation"));
    }
}