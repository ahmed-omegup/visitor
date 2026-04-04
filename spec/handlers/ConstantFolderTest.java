package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;
import lib.visitors.ConstantFolder;
import port.IFactory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import lib.expression.*;

abstract class ConstantFolderTestBase<E extends Expression> extends TestBase<E> {
    ConstantFolderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collapsesArithmeticBranches() {
        var folder = testSupport.v.constantFolder();
        var folded =factory.addition(
                factory.multiplication(factory.literal("2"), factory.literal("3")),
                factory.literal("4")
            ).accept(folder);

        assertLiteralValue("10", folded, "constant arithmetic should fold to one literal");
    }

    @Test
    void collapsesComparisonsAndBooleanOperators() {
        var folder = testSupport.v.constantFolder();

        assertLiteralValue("1",factory.equality(factory.literal("8"), factory.literal("8")).accept(folder), "equality should fold");
        assertLiteralValue("1",factory.inequality(factory.literal("8"), factory.literal("2")).accept(folder), "inequality should fold");
        assertLiteralValue("1",factory.lessThan(factory.literal("2"), factory.literal("8")).accept(folder), "less-than should fold");
        assertLiteralValue("1",factory.greaterThan(factory.literal("8"), factory.literal("2")).accept(folder), "greater-than should fold");
        assertLiteralValue("1",factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")).accept(folder), "less-than-or-equal should fold");
        assertLiteralValue("1",factory.greaterThanOrEqual(factory.literal("8"), factory.literal("8")).accept(folder), "greater-than-or-equal should fold");
        assertLiteralValue("1",factory.conjunction(factory.literal("1"), factory.literal("4")).accept(folder), "conjunction should fold");
        assertLiteralValue("1",factory.disjunction(factory.literal("0"), factory.literal("4")).accept(folder), "disjunction should fold");
        assertLiteralValue("1",factory.logicalNot(factory.literal("0")).accept(folder), "logical-not should fold");
        assertLiteralValue("8",factory.exponentiation(factory.literal("2"), factory.literal("3")).accept(folder), "exponentiation should fold");
        assertLiteralValue("1",factory.modulo(factory.literal("7"), factory.literal("3")).accept(folder), "modulo should fold");
        assertLiteralValue("-3",factory.negation(factory.literal("3")).accept(folder), "negation should fold");
    }

    @Test
    void resolvesConstantConditional() {
        var folder = testSupport.v.constantFolder();
        var folded =factory.conditional(
                factory.logicalNot(factory.literal("0")),
                factory.literal("11"),
                factory.literal("22")
            ).accept(folder);

        assertLiteralValue("11", folded, "constant condition should choose the true branch");
    }

    @Test
    void preservesDynamicExpressions() {
        var folder = testSupport.v.constantFolder();
        var folded =factory.addition(factory.variableReference("x"), factory.literal("2")).accept(folder);

        var addition = assertInstanceOf(Addition.class, folded, "dynamic addition should remain an Addition");
        assertVariableReferenceName("x", addition.left, "left operand should remain the original variable reference");
        assertLiteralValue("2", addition.right, "right operand should remain the literal value");
    }

    @Test
    void visitsEveryExpressionType() {
        var folder = testSupport.v.constantFolder();

        assertLiteralValue("3",factory.literal("3").accept(folder), "literal should remain unchanged");
        assertVariableReferenceName("x",factory.variableReference("x").accept(folder), "variable reference should remain unchanged");
        assertLiteralValue("3",factory.addition(factory.literal("1"), factory.literal("2")).accept(folder), "addition should fold");
        assertLiteralValue("1",factory.subtraction(factory.literal("3"), factory.literal("2")).accept(folder), "subtraction should fold");
        assertLiteralValue("6",factory.multiplication(factory.literal("3"), factory.literal("2")).accept(folder), "multiplication should fold");
        assertLiteralValue("2",factory.division(factory.literal("6"), factory.literal("3")).accept(folder), "division should fold");
        assertLiteralValue("-2",factory.negation(factory.literal("2")).accept(folder), "negation should fold");
        assertLiteralValue("1",factory.modulo(factory.literal("7"), factory.literal("3")).accept(folder), "modulo should fold");
        assertLiteralValue("8",factory.exponentiation(factory.literal("2"), factory.literal("3")).accept(folder), "exponentiation should fold");
        assertLiteralValue("1",factory.equality(factory.literal("2"), factory.literal("2")).accept(folder), "equality should fold");
        assertLiteralValue("1",factory.inequality(factory.literal("2"), factory.literal("3")).accept(folder), "inequality should fold");
        assertLiteralValue("1",factory.lessThan(factory.literal("2"), factory.literal("3")).accept(folder), "less-than should fold");
        assertLiteralValue("1",factory.greaterThan(factory.literal("3"), factory.literal("2")).accept(folder), "greater-than should fold");
        assertLiteralValue("1",factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")).accept(folder), "less-than-or-equal should fold");
        assertLiteralValue("1",factory.greaterThanOrEqual(factory.literal("3"), factory.literal("3")).accept(folder), "greater-than-or-equal should fold");
        assertLiteralValue("1",factory.conjunction(factory.literal("1"), factory.literal("1")).accept(folder), "conjunction should fold");
        assertLiteralValue("1",factory.disjunction(factory.literal("0"), factory.literal("1")).accept(folder), "disjunction should fold");
        assertLiteralValue("1",factory.logicalNot(factory.literal("0")).accept(folder), "logical-not should fold");
        assertLiteralValue("4",factory.conditional(factory.literal("1"), factory.literal("4"), factory.literal("5")).accept(folder), "conditional should fold");

        var functionCall =factory.functionCall(factory.variableReference("sum"), java.util.List.of( factory.addition(factory.literal("1"), factory.literal("2")), factory.literal("4"))).accept(folder);
        var call = assertInstanceOf(FunctionCall.class, functionCall, "function call should remain a FunctionCall");
        assertVariableReferenceName("sum", call.callee, "function call callee should remain a variable reference");
        assertLiteralValue("3", call.arguments[0], "function call arguments should be folded");
        assertLiteralValue("4", call.arguments[1], "function call arguments should be preserved");
    }

    private void assertLiteralValue(String expected, lib.expression.Expression expression, String message) {
        var literal = assertInstanceOf(Literal.class, expression, message);
        org.junit.jupiter.api.Assertions.assertEquals(expected, literal.value, message);
    }

    private void assertVariableReferenceName(String expected, lib.expression.Expression expression, String message) {
        var variable = assertInstanceOf(VariableReference.class, expression, message);
        org.junit.jupiter.api.Assertions.assertEquals(expected, variable.name, message);
    }
}

class ConstantFolderTest extends ConstantFolderTestBase<Expression> {
    ConstantFolderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
