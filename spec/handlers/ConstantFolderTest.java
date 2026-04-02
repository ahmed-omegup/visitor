package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.FunctionCall;
import lib.expression.GreaterThan;
import lib.expression.GreaterThanOrEqual;
import lib.expression.Inequality;
import lib.expression.LessThan;
import lib.expression.LessThanOrEqual;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Modulo;
import lib.expression.Multiplication;
import lib.expression.Negation;
import lib.expression.Subtraction;
import lib.expression.VariableReference;
import lib.handlers.ConstantFolder;

class ConstantFolderTest {
    @Test
    void collapsesArithmeticBranches() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            addition(
                multiplication(literal("2"), literal("3")),
                literal("4")
            )
        );

        assertLiteralValue("10", folded, "constant arithmetic should fold to one literal");
    }

    @Test
    void collapsesComparisonsAndBooleanOperators() {
        var folder = new ConstantFolder();

        assertLiteralValue("1", folder.handle(equality(literal("8"), literal("8"))), "equality should fold");
        assertLiteralValue("1", folder.handle(inequality(literal("8"), literal("2"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(lessThan(literal("2"), literal("8"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(greaterThan(literal("8"), literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(lessThanOrEqual(literal("2"), literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(greaterThanOrEqual(literal("8"), literal("8"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(conjunction(literal("1"), literal("4"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(disjunction(literal("0"), literal("4"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(logicalNot(literal("0"))), "logical-not should fold");
        assertLiteralValue("8", folder.handle(exponentiation(literal("2"), literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(modulo(literal("7"), literal("3"))), "modulo should fold");
        assertLiteralValue("-3", folder.handle(negation(literal("3"))), "negation should fold");
    }

    @Test
    void resolvesConstantConditional() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            conditional(
                logicalNot(literal("0")),
                literal("11"),
                literal("22")
            )
        );

        assertLiteralValue("11", folded, "constant condition should choose the true branch");
    }

    @Test
    void preservesDynamicExpressions() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            addition(variableReference("x"), literal("2"))
        );

        var addition = assertInstanceOf(Addition.class, folded, "dynamic addition should remain an Addition");
        assertVariableReferenceName("x", addition.left, "left operand should remain the original variable reference");
        assertLiteralValue("2", addition.right, "right operand should remain the literal value");
    }

    @Test
    void visitsEveryExpressionType() {
        var folder = new ConstantFolder();

        assertLiteralValue("3", folder.handle(literal("3")), "literal should remain unchanged");
        assertVariableReferenceName("x", folder.handle(variableReference("x")), "variable reference should remain unchanged");
        assertLiteralValue("3", folder.handle(addition(literal("1"), literal("2"))), "addition should fold");
        assertLiteralValue("1", folder.handle(subtraction(literal("3"), literal("2"))), "subtraction should fold");
        assertLiteralValue("6", folder.handle(multiplication(literal("3"), literal("2"))), "multiplication should fold");
        assertLiteralValue("2", folder.handle(division(literal("6"), literal("3"))), "division should fold");
        assertLiteralValue("-2", folder.handle(negation(literal("2"))), "negation should fold");
        assertLiteralValue("1", folder.handle(modulo(literal("7"), literal("3"))), "modulo should fold");
        assertLiteralValue("8", folder.handle(exponentiation(literal("2"), literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(equality(literal("2"), literal("2"))), "equality should fold");
        assertLiteralValue("1", folder.handle(inequality(literal("2"), literal("3"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(lessThan(literal("2"), literal("3"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(greaterThan(literal("3"), literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(lessThanOrEqual(literal("2"), literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(greaterThanOrEqual(literal("3"), literal("3"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(conjunction(literal("1"), literal("1"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(disjunction(literal("0"), literal("1"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(logicalNot(literal("0"))), "logical-not should fold");
        assertLiteralValue("4", folder.handle(conditional(literal("1"), literal("4"), literal("5"))), "conditional should fold");

        var functionCall = folder.handle(functionCall(variableReference("sum"), addition(literal("1"), literal("2")), literal("4")));
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