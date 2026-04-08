package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.ExpressionV1;
import lib.handlers.HandlerFactory;

abstract class HandlerFactoryHelpersTestBase<E> extends TestBase<E> {
    HandlerFactoryHelpersTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }

    @Test
    void literalAndVariableCheckersRecognizeNodeKinds() {
        assertTrue(testSupport.v.literalChecker().apply(factory.literal("8")));
        assertFalse(testSupport.v.literalChecker().apply(factory.addition(factory.literal("1"), factory.literal("2"))));
        assertTrue(testSupport.v.variableChecker().apply(factory.variableReference("x")));
        assertFalse(testSupport.v.variableChecker().apply(factory.negation(factory.literal("3"))));
    }

    @Test
    void bindingPowerHandlerUsesExpectedPriorities() {
        var bindingPower = testSupport.v.createBindingPowerHandler();
        var addition = bindingPower.apply(factory.addition(factory.literal("1"), factory.literal("2")));
        var exponentiation = bindingPower.apply(factory.exponentiation(factory.literal("2"), factory.literal("3")));
        var conditional = bindingPower.apply(testSupport.sampleTraversalExpression());

        assertEquals(10, addition.priority());
        assertFalse(addition.isRightAssociative());
        assertEquals(40, exponentiation.priority());
        assertTrue(exponentiation.isRightAssociative());
        assertEquals(1, conditional.priority());
    }

    @Test
    void constantFolderOnceHandlesFoldableOperatorsAndShortCircuit() {
        var foldOnce = testSupport.v.constantFolderOnce();

        assertEquals("3", render(foldOnce.apply(factory.addition(factory.literal("1"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.subtraction(factory.literal("3"), factory.literal("2")))));
        assertEquals("6", render(foldOnce.apply(factory.multiplication(factory.literal("3"), factory.literal("2")))));
        assertEquals("2", render(foldOnce.apply(factory.division(factory.literal("6"), factory.literal("3")))));
        assertEquals("1", render(foldOnce.apply(factory.modulo(factory.literal("7"), factory.literal("3")))));
        assertEquals("8", render(foldOnce.apply(factory.exponentiation(factory.literal("2"), factory.literal("3")))));
        assertEquals("-2", render(foldOnce.apply(factory.negation(factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.equality(factory.literal("2"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.inequality(factory.literal("2"), factory.literal("3")))));
        assertEquals("1", render(foldOnce.apply(factory.lessThan(factory.literal("2"), factory.literal("3")))));
        assertEquals("1", render(foldOnce.apply(factory.greaterThan(factory.literal("3"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.greaterThanOrEqual(factory.literal("3"), factory.literal("3")))));
        assertEquals("0", render(foldOnce.apply(factory.conjunction(factory.literal("0"), factory.variableReference("x")))));
        assertEquals("y", render(foldOnce.apply(factory.conjunction(factory.literal("1"), factory.variableReference("y")))));
        assertEquals("1", render(foldOnce.apply(factory.disjunction(factory.literal("1"), factory.variableReference("x")))));
        assertEquals("y", render(foldOnce.apply(factory.disjunction(factory.literal("0"), factory.variableReference("y")))));
        assertEquals("1", render(foldOnce.apply(factory.logicalNot(factory.literal("0")))));
        assertEquals("22", render(foldOnce.apply(factory.conditional(factory.literal("0"), factory.literal("11"), factory.literal("22")))));
        assertEquals("f(1)", render(foldOnce.apply(factory.functionCall(factory.variableReference("f"), of(factory.literal("1"))))));
        assertEquals("x", render(foldOnce.apply(factory.variableReference("x"))));
        assertEquals("4", render(foldOnce.apply(factory.literal("4"))));
    }

    @Test
    void histogramCountsNodeKindsAcrossTraversalExpression() {
        var histogram = testSupport.v.histogram().apply(testSupport.sampleTraversalExpression());

        assertEquals(22, histogram.literal());
        assertEquals(2, histogram.variableReference());
        assertEquals(1, histogram.conditional());
        assertEquals(1, histogram.functionCall());
        assertEquals(1, histogram.addition());
        assertEquals(1, histogram.negation());
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

    @Test
    void renameVariableRewritesMatchingReferencesOnly() {
        var renamed = testSupport.v.renameVariable("x", "y").apply(testSupport.sampleTraversalExpression());

        assertEquals(
            "y < 10 && !(1 == 0) ? 7 - 2 + 8 / 2 * (9 % 4) : f(pow(2, 3), 5 != 6, 7 > 1, 2 <= 2, 3 >= 3, 0 || 1, -4)",
            render(renamed)
        );
    }
}

class HandlerFactoryHelpersTest extends HandlerFactoryHelpersTestBase<ExpressionV1> {
    HandlerFactoryHelpersTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}