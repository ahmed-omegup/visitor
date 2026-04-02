package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.BreadthFirstLabelPrinter;

class BreadthFirstLabelPrinterTest {
    @Test
    void printsLabelsBreadthFirst() {
        assertEquals(
            "Addition | VariableReference(x) | Negation | Literal(2)",
            new BreadthFirstLabelPrinter().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.negation(lib.expression.Expression.literal("2"))))
        );
    }

    @Test
    void printsTraversalExpressionBreadthFirst() {
        var labels = new BreadthFirstLabelPrinter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(labels.startsWith("Conditional | Conjunction | Addition | FunctionCall | LessThan"));
        assertTrue(labels.contains("GreaterThanOrEqual | Disjunction | Negation"));
    }
}