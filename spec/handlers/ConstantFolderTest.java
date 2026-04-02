package spec.handlers;

import lib.expression.Factory;
import port.IFactory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.handlers.ConstantFolder;

class ConstantFolderTest {
    private final IFactory factory = new Factory();
    @Test
    void collapsesArithmeticBranches() {
        var folder = new ConstantFolder(factory);
        var folded = folder.handle(
            factory.addition(
                factory.multiplication(factory.literal("2"), factory.literal("3")),
                factory.literal("4")
            )
        );

        assertLiteralValue("10", folded, "constant arithmetic should fold to one literal");
    }

    @Test
    void collapsesComparisonsAndBooleanOperators() {
        var folder = new ConstantFolder(factory);

        assertLiteralValue("1", folder.handle(factory.equality(factory.literal("8"), factory.literal("8"))), "equality should fold");
        assertLiteralValue("1", folder.handle(factory.inequality(factory.literal("8"), factory.literal("2"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(factory.lessThan(factory.literal("2"), factory.literal("8"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(factory.greaterThan(factory.literal("8"), factory.literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(factory.greaterThanOrEqual(factory.literal("8"), factory.literal("8"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(factory.conjunction(factory.literal("1"), factory.literal("4"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(factory.disjunction(factory.literal("0"), factory.literal("4"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(factory.logicalNot(factory.literal("0"))), "logical-not should fold");
        assertLiteralValue("8", folder.handle(factory.exponentiation(factory.literal("2"), factory.literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(factory.modulo(factory.literal("7"), factory.literal("3"))), "modulo should fold");
        assertLiteralValue("-3", folder.handle(factory.negation(factory.literal("3"))), "negation should fold");
    }

    @Test
    void resolvesConstantConditional() {
        var folder = new ConstantFolder(factory);
        var folded = folder.handle(
            factory.conditional(
                factory.logicalNot(factory.literal("0")),
                factory.literal("11"),
                factory.literal("22")
            )
        );

        assertLiteralValue("11", folded, "constant condition should choose the true branch");
    }

    @Test
    void preservesDynamicExpressions() {
        var folder = new ConstantFolder(factory);
        var folded = folder.handle(
            factory.addition(factory.variableReference("x"), factory.literal("2"))
        );

        var addition = assertInstanceOf(Addition.class, folded, "dynamic addition should remain an Addition");
        assertVariableReferenceName("x", addition.left, "left operand should remain the original variable reference");
        assertLiteralValue("2", addition.right, "right operand should remain the literal value");
    }

    @Test
    void visitsEveryExpressionType() {
        var folder = new ConstantFolder(factory);

        assertLiteralValue("3", folder.handle(factory.literal("3")), "literal should remain unchanged");
        assertVariableReferenceName("x", folder.handle(factory.variableReference("x")), "variable reference should remain unchanged");
        assertLiteralValue("3", folder.handle(factory.addition(factory.literal("1"), factory.literal("2"))), "addition should fold");
        assertLiteralValue("1", folder.handle(factory.subtraction(factory.literal("3"), factory.literal("2"))), "subtraction should fold");
        assertLiteralValue("6", folder.handle(factory.multiplication(factory.literal("3"), factory.literal("2"))), "multiplication should fold");
        assertLiteralValue("2", folder.handle(factory.division(factory.literal("6"), factory.literal("3"))), "division should fold");
        assertLiteralValue("-2", folder.handle(factory.negation(factory.literal("2"))), "negation should fold");
        assertLiteralValue("1", folder.handle(factory.modulo(factory.literal("7"), factory.literal("3"))), "modulo should fold");
        assertLiteralValue("8", folder.handle(factory.exponentiation(factory.literal("2"), factory.literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(factory.equality(factory.literal("2"), factory.literal("2"))), "equality should fold");
        assertLiteralValue("1", folder.handle(factory.inequality(factory.literal("2"), factory.literal("3"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(factory.lessThan(factory.literal("2"), factory.literal("3"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(factory.greaterThan(factory.literal("3"), factory.literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(factory.greaterThanOrEqual(factory.literal("3"), factory.literal("3"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(factory.conjunction(factory.literal("1"), factory.literal("1"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(factory.disjunction(factory.literal("0"), factory.literal("1"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(factory.logicalNot(factory.literal("0"))), "logical-not should fold");
        assertLiteralValue("4", folder.handle(factory.conditional(factory.literal("1"), factory.literal("4"), factory.literal("5"))), "conditional should fold");

        var functionCall = folder.handle(factory.functionCall(factory.variableReference("sum"), factory.addition(factory.literal("1"), factory.literal("2")), factory.literal("4")));
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