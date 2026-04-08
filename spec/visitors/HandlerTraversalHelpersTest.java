package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.ExpressionV1;
import lib.handlers.HandlerFactory;

abstract class HandlerTraversalHelpersTestBase<E> extends TestBase<E> {
    HandlerTraversalHelpersTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }

    @Test
    void histogramCountsNodeKindsAcrossTraversalExpression() {
        var histogram = testSupport.v.histogram().apply(testSupport.sampleTraversalExpression());

        assertEquals(22, histogram.literal());
        assertEquals(2, histogram.variableReference());
        assertEquals(1, histogram.addition());
        assertEquals(1, histogram.subtraction());
        assertEquals(1, histogram.multiplication());
        assertEquals(1, histogram.division());
        assertEquals(1, histogram.negation());
        assertEquals(1, histogram.modulo());
        assertEquals(1, histogram.exponentiation());
        assertEquals(1, histogram.equality());
        assertEquals(1, histogram.inequality());
        assertEquals(1, histogram.lessThan());
        assertEquals(1, histogram.greaterThan());
        assertEquals(1, histogram.lessThanOrEqual());
        assertEquals(1, histogram.greaterThanOrEqual());
        assertEquals(1, histogram.conjunction());
        assertEquals(1, histogram.disjunction());
        assertEquals(1, histogram.logicalNot());
        assertEquals(1, histogram.conditional());
        assertEquals(1, histogram.functionCall());
    }

    @Test
    void collectClassNamesVisitorTraversesExpressionInPreOrder() {
        var classNames = testSupport.v.collectClassNamesVisitor().apply(testSupport.sampleTraversalExpression());

        assertEquals(of(
            "Conditional",
            "Conjunction",
            "LessThan",
            "VariableReference",
            "Literal",
            "LogicalNot",
            "Equality",
            "Literal",
            "Literal",
            "Addition",
            "Subtraction",
            "Literal",
            "Literal",
            "Multiplication",
            "Division",
            "Literal",
            "Literal",
            "Modulo",
            "Literal",
            "Literal",
            "FunctionCall",
            "VariableReference",
            "Exponentiation",
            "Literal",
            "Literal",
            "Inequality",
            "Literal",
            "Literal",
            "GreaterThan",
            "Literal",
            "Literal",
            "LessThanOrEqual",
            "Literal",
            "Literal",
            "GreaterThanOrEqual",
            "Literal",
            "Literal",
            "Disjunction",
            "Literal",
            "Literal",
            "Negation",
            "Literal"
        ), classNames);
    }
}

class HandlerTraversalHelpersTest extends HandlerTraversalHelpersTestBase<ExpressionV1> {
    HandlerTraversalHelpersTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}