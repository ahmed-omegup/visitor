package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.RootToLeafTracePrinter;

abstract class RootToLeafTracePrinterTestBase<E> extends TestBase<E> {
    RootToLeafTracePrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void printsOneTracePerLeaf() {
        assertEquals(
            String.join("\n", "Addition -> VariableReference(x)", "Addition -> Negation -> Literal(2)"),
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(testSupport.v.rootToLeafTracePrinter())
        );
    }

    @Test
    void printsTraversalExpressionRootToLeafTraces() {
        var traces =testSupport.sampleTraversalExpression().accept(testSupport.v.rootToLeafTracePrinter()).lines().toList();

        assertEquals(24, traces.size());
        assertEquals("Conditional -> Conjunction -> LessThan -> VariableReference(x)", traces.get(0));
        assertTrue(traces.contains("Conditional -> Conjunction -> LogicalNot -> Equality -> Literal(0)"));
        assertTrue(traces.contains("Conditional -> Addition -> Multiplication -> Division -> Literal(8)"));
        assertTrue(traces.contains("Conditional -> FunctionCall -> VariableReference(f)"));
        assertEquals("Conditional -> FunctionCall -> Negation -> Literal(4)", traces.get(traces.size() - 1));
    }
}

class RootToLeafTracePrinterTest extends RootToLeafTracePrinterTestBase<Expression> {
    RootToLeafTracePrinterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
