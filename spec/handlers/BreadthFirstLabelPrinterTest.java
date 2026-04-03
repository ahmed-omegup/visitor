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
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(TestSupport.handlers().breadthFirstLabelPrinter())
        );
    }

    @Test
    void printsTraversalExpressionBreadthFirst() {
        var labels =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().breadthFirstLabelPrinter());

        assertTrue(labels.startsWith("Conditional | Conjunction | Addition | FunctionCall | LessThan"));
        assertTrue(labels.contains("GreaterThanOrEqual | Disjunction | Negation"));
    }
}