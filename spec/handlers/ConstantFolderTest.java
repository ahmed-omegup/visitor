package spec.handlers;

import java.util.function.Function;

import lib.expression.Expression;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import port.IHandlerFactory.Tree;

abstract class ConstantFolderTestBase<E> extends TestBase<E> {
    ConstantFolderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collapsesArithmeticBranches() {
        var folder = testSupport.v.constantFolder();
        var folded =folder.apply(factory.addition(
                factory.multiplication(factory.literal("2"), factory.literal("3")),
                factory.literal("4")
            ));

        assertLiteralValue("10", folded, "constant arithmetic should fold to one literal");
    }

    @Test
    void collapsesComparisonsAndBooleanOperators() {
        var folder = testSupport.v.constantFolder();

        assertLiteralValue("1",folder.apply(factory.equality(factory.literal("8"), factory.literal("8"))), "equality should fold");
        assertLiteralValue("1",folder.apply(factory.inequality(factory.literal("8"), factory.literal("2"))), "inequality should fold");
        assertLiteralValue("1",folder.apply(factory.lessThan(factory.literal("2"), factory.literal("8"))), "less-than should fold");
        assertLiteralValue("1",folder.apply(factory.greaterThan(factory.literal("8"), factory.literal("2"))), "greater-than should fold");
        assertLiteralValue("1",folder.apply(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1",folder.apply(factory.greaterThanOrEqual(factory.literal("8"), factory.literal("8"))), "greater-than-or-equal should fold");
        assertLiteralValue("1",folder.apply(factory.conjunction(factory.literal("1"), factory.literal("4"))), "conjunction should fold");
        assertLiteralValue("1",folder.apply(factory.disjunction(factory.literal("0"), factory.literal("4"))), "disjunction should fold");
        assertLiteralValue("1",folder.apply(factory.logicalNot(factory.literal("0"))), "logical-not should fold");
        assertLiteralValue("8",folder.apply(factory.exponentiation(factory.literal("2"), factory.literal("3"))), "exponentiation should fold");
        assertLiteralValue("1",folder.apply(factory.modulo(factory.literal("7"), factory.literal("3"))), "modulo should fold");
        assertLiteralValue("-3",folder.apply(factory.negation(factory.literal("3"))), "negation should fold");
    }

    @Test
    void resolvesConstantConditional() {
        var folder = testSupport.v.constantFolder();
        var folded =folder.apply(factory.conditional(
                factory.logicalNot(factory.literal("0")),
                factory.literal("11"),
                factory.literal("22")
            ));

        assertLiteralValue("11", folded, "constant condition should choose the true branch");
    }

    @Test
    void preservesDynamicExpressions() {
        var folder = testSupport.v.constantFolder();
        var folded = folder.apply(factory.addition(factory.variableReference("x"), factory.literal("2")));

        var addition = assertTypeName("Addition", folded, "dynamic addition should remain an Addition");
        assertVariableReferenceName("x", childExpression(addition, 0), "left operand should remain the original variable reference");
        assertLiteralValue("2", childExpression(addition, 1), "right operand should remain the literal value");
    }

    @Test
    void visitsEveryExpressionType() {
        var folder = testSupport.v.constantFolder();

        assertLiteralValue("3",folder.apply(factory.literal("3")), "literal should remain unchanged");
        assertVariableReferenceName("x",folder.apply(factory.variableReference("x")), "variable reference should remain unchanged");
        assertLiteralValue("3",folder.apply(factory.addition(factory.literal("1"), factory.literal("2"))), "addition should fold");
        assertLiteralValue("1",folder.apply(factory.subtraction(factory.literal("3"), factory.literal("2"))), "subtraction should fold");
        assertLiteralValue("6",folder.apply(factory.multiplication(factory.literal("3"), factory.literal("2"))), "multiplication should fold");
        assertLiteralValue("2",folder.apply(factory.division(factory.literal("6"), factory.literal("3"))), "division should fold");
        assertLiteralValue("-2",folder.apply(factory.negation(factory.literal("2"))), "negation should fold");
        assertLiteralValue("1",folder.apply(factory.modulo(factory.literal("7"), factory.literal("3"))), "modulo should fold");
        assertLiteralValue("8",folder.apply(factory.exponentiation(factory.literal("2"), factory.literal("3"))), "exponentiation should fold");
        assertLiteralValue("1",folder.apply(factory.equality(factory.literal("2"), factory.literal("2"))), "equality should fold");
        assertLiteralValue("1",folder.apply(factory.inequality(factory.literal("2"), factory.literal("3"))), "inequality should fold");
        assertLiteralValue("1",folder.apply(factory.lessThan(factory.literal("2"), factory.literal("3"))), "less-than should fold");
        assertLiteralValue("1",folder.apply(factory.greaterThan(factory.literal("3"), factory.literal("2"))), "greater-than should fold");
        assertLiteralValue("1",folder.apply(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1",folder.apply(factory.greaterThanOrEqual(factory.literal("3"), factory.literal("3"))), "greater-than-or-equal should fold");
        assertLiteralValue("1",folder.apply(factory.conjunction(factory.literal("1"), factory.literal("1"))), "conjunction should fold");
        assertLiteralValue("1",folder.apply(factory.disjunction(factory.literal("0"), factory.literal("1"))), "disjunction should fold");
        assertLiteralValue("1",folder.apply(factory.logicalNot(factory.literal("0"))), "logical-not should fold");
        assertLiteralValue("4",folder.apply(factory.conditional(factory.literal("1"), factory.literal("4"), factory.literal("5"))), "conditional should fold");

        var functionCall = folder.apply(factory.functionCall(factory.variableReference("sum"), of(factory.addition(factory.literal("1"), factory.literal("2")), factory.literal("4"))));
        var call = assertTypeName("FunctionCall", functionCall, "function call should remain a FunctionCall");
        assertVariableReferenceName("sum", childExpression(call, 0), "function call callee should remain a variable reference");
        assertLiteralValue("3", childExpression(call, 1), "function call arguments should be folded");
        assertLiteralValue("4", childExpression(call, 2), "function call arguments should be preserved");
    }

    private void assertLiteralValue(String expected, E expression, String message) {
        assertTypeName("Literal", expression, message);
        assertEquals(expected, render(expression), message);
    }

    private void assertVariableReferenceName(String expected, E expression, String message) {
        assertTypeName("VariableReference", expression, message);
        assertEquals(expected, render(expression), message);
    }

    private Tree<E> assertTypeName(String expected, E expression, String message) {
        assertEquals(expected, typeName(expression), message);
        return tree(expression);
    }

    private E childExpression(Tree<E> tree, int childIndex) {
        return tree.children().get(childIndex).value();
    }

    protected abstract Tree<E> tree(E expression);
}

class ConstantFolderTest extends ConstantFolderTestBase<Expression> {
    ConstantFolderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }

    @Override
    protected Tree<Expression> tree(Expression expression) {
        return testSupport.v.expressionTreeBuilder(Function.identity()).apply(expression);
    }
}
