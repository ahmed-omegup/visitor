package spec.handlers;

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
            lib.expression.Expression.addition(
                lib.expression.Expression.multiplication(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3")),
                lib.expression.Expression.literal("4")
            )
        );

        assertLiteralValue("10", folded, "constant arithmetic should fold to one literal");
    }

    @Test
    void collapsesComparisonsAndBooleanOperators() {
        var folder = new ConstantFolder();

        assertLiteralValue("1", folder.handle(lib.expression.Expression.equality(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("8"))), "equality should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.inequality(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.lessThan(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("8"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.greaterThan(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.lessThanOrEqual(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.greaterThanOrEqual(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("8"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.conjunction(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("4"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.disjunction(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("4"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.logicalNot(lib.expression.Expression.literal("0"))), "logical-not should fold");
        assertLiteralValue("8", folder.handle(lib.expression.Expression.exponentiation(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.modulo(lib.expression.Expression.literal("7"), lib.expression.Expression.literal("3"))), "modulo should fold");
        assertLiteralValue("-3", folder.handle(lib.expression.Expression.negation(lib.expression.Expression.literal("3"))), "negation should fold");
    }

    @Test
    void resolvesConstantConditional() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            lib.expression.Expression.conditional(
                lib.expression.Expression.logicalNot(lib.expression.Expression.literal("0")),
                lib.expression.Expression.literal("11"),
                lib.expression.Expression.literal("22")
            )
        );

        assertLiteralValue("11", folded, "constant condition should choose the true branch");
    }

    @Test
    void preservesDynamicExpressions() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("2"))
        );

        var addition = assertInstanceOf(Addition.class, folded, "dynamic addition should remain an Addition");
        assertVariableReferenceName("x", addition.left, "left operand should remain the original variable reference");
        assertLiteralValue("2", addition.right, "right operand should remain the literal value");
    }

    @Test
    void visitsEveryExpressionType() {
        var folder = new ConstantFolder();

        assertLiteralValue("3", folder.handle(lib.expression.Expression.literal("3")), "literal should remain unchanged");
        assertVariableReferenceName("x", folder.handle(lib.expression.Expression.variableReference("x")), "variable reference should remain unchanged");
        assertLiteralValue("3", folder.handle(lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"))), "addition should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.subtraction(lib.expression.Expression.literal("3"), lib.expression.Expression.literal("2"))), "subtraction should fold");
        assertLiteralValue("6", folder.handle(lib.expression.Expression.multiplication(lib.expression.Expression.literal("3"), lib.expression.Expression.literal("2"))), "multiplication should fold");
        assertLiteralValue("2", folder.handle(lib.expression.Expression.division(lib.expression.Expression.literal("6"), lib.expression.Expression.literal("3"))), "division should fold");
        assertLiteralValue("-2", folder.handle(lib.expression.Expression.negation(lib.expression.Expression.literal("2"))), "negation should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.modulo(lib.expression.Expression.literal("7"), lib.expression.Expression.literal("3"))), "modulo should fold");
        assertLiteralValue("8", folder.handle(lib.expression.Expression.exponentiation(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.equality(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2"))), "equality should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.inequality(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.lessThan(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.greaterThan(lib.expression.Expression.literal("3"), lib.expression.Expression.literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.lessThanOrEqual(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.greaterThanOrEqual(lib.expression.Expression.literal("3"), lib.expression.Expression.literal("3"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.conjunction(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("1"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.disjunction(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("1"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(lib.expression.Expression.logicalNot(lib.expression.Expression.literal("0"))), "logical-not should fold");
        assertLiteralValue("4", folder.handle(lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("4"), lib.expression.Expression.literal("5"))), "conditional should fold");

        var functionCall = folder.handle(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")), lib.expression.Expression.literal("4")));
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