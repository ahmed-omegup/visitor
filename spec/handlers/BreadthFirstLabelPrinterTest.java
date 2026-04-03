package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.BreadthFirstLabelPrinter;
import port.IFactory;

class BreadthFirstLabelPrinterTest {
    private final IFactory factory = new Factory();
    @Test
    void printsLabelsBreadthFirst() {
        assertEquals(
            "Addition | VariableReference(x) | Negation | Literal(2)",
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(v.breadthFirstLabelPrinter())
        );
    }

    @Test
    void printsTraversalExpressionBreadthFirst() {
        var labels =sampleTraversalExpression().accept(v.breadthFirstLabelPrinter());

        assertTrue(labels.startsWith("Conditional | Conjunction | Addition | FunctionCall | LessThan"));
        assertTrue(labels.contains("GreaterThanOrEqual | Disjunction | Negation"));
    }
}