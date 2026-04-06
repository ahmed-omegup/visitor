package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.BreadthFirstLabelPrinter;
import lib.handlers.HandlerFactory;

abstract class BreadthFirstLabelPrinterTestBase<E> extends TestBase<E> {
    BreadthFirstLabelPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void printsLabelsBreadthFirst() {
        assertEquals(
            "Addition | VariableReference(x) | Negation | Literal(2)",testSupport.v.breadthFirstLabelPrinter().apply(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void printsTraversalExpressionBreadthFirst() {
        var labels =testSupport.v.breadthFirstLabelPrinter().apply(testSupport.sampleTraversalExpression());

        assertTrue(labels.startsWith("Conditional | Conjunction | Addition | FunctionCall | LessThan"));
        assertTrue(labels.contains("GreaterThanOrEqual | Disjunction | Negation"));
    }
}

class BreadthFirstLabelPrinterTest extends BreadthFirstLabelPrinterTestBase<Expression> {
    BreadthFirstLabelPrinterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
