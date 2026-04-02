package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Disjunction;
import visitor.expression.Division;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.FunctionCall;
import visitor.expression.GreaterThan;
import visitor.expression.GreaterThanOrEqual;
import visitor.expression.Inequality;
import visitor.expression.LessThan;
import visitor.expression.LessThanOrEqual;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Modulo;
import visitor.expression.Multiplication;
import visitor.expression.Negation;
import visitor.expression.Subtraction;
import visitor.expression.VariableReference;
import visitor.handlers.ConstantFolder;

class ConstantFolderTest {
    @Test
    void collapsesArithmeticBranches() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            new Addition(
                new Multiplication(new Literal("2"), new Literal("3")),
                new Literal("4")
            )
        );

        assertLiteralValue("10", folded, "constant arithmetic should fold to one literal");
    }

    @Test
    void collapsesComparisonsAndBooleanOperators() {
        var folder = new ConstantFolder();

        assertLiteralValue("1", folder.handle(new Equality(new Literal("8"), new Literal("8"))), "equality should fold");
        assertLiteralValue("1", folder.handle(new Inequality(new Literal("8"), new Literal("2"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(new LessThan(new Literal("2"), new Literal("8"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(new GreaterThan(new Literal("8"), new Literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(new LessThanOrEqual(new Literal("2"), new Literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(new GreaterThanOrEqual(new Literal("8"), new Literal("8"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(new Conjunction(new Literal("1"), new Literal("4"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(new Disjunction(new Literal("0"), new Literal("4"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(new LogicalNot(new Literal("0"))), "logical-not should fold");
        assertLiteralValue("8", folder.handle(new Exponentiation(new Literal("2"), new Literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(new Modulo(new Literal("7"), new Literal("3"))), "modulo should fold");
        assertLiteralValue("-3", folder.handle(new Negation(new Literal("3"))), "negation should fold");
    }

    @Test
    void resolvesConstantConditional() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            new Conditional(
                new LogicalNot(new Literal("0")),
                new Literal("11"),
                new Literal("22")
            )
        );

        assertLiteralValue("11", folded, "constant condition should choose the true branch");
    }

    @Test
    void preservesDynamicExpressions() {
        var folder = new ConstantFolder();
        var folded = folder.handle(
            new Addition(new VariableReference("x"), new Literal("2"))
        );

        var addition = assertInstanceOf(Addition.class, folded, "dynamic addition should remain an Addition");
        assertVariableReferenceName("x", addition.left, "left operand should remain the original variable reference");
        assertLiteralValue("2", addition.right, "right operand should remain the literal value");
    }

    @Test
    void visitsEveryExpressionType() {
        var folder = new ConstantFolder();

        assertLiteralValue("3", folder.handle(new Literal("3")), "literal should remain unchanged");
        assertVariableReferenceName("x", folder.handle(new VariableReference("x")), "variable reference should remain unchanged");
        assertLiteralValue("3", folder.handle(new Addition(new Literal("1"), new Literal("2"))), "addition should fold");
        assertLiteralValue("1", folder.handle(new Subtraction(new Literal("3"), new Literal("2"))), "subtraction should fold");
        assertLiteralValue("6", folder.handle(new Multiplication(new Literal("3"), new Literal("2"))), "multiplication should fold");
        assertLiteralValue("2", folder.handle(new Division(new Literal("6"), new Literal("3"))), "division should fold");
        assertLiteralValue("-2", folder.handle(new Negation(new Literal("2"))), "negation should fold");
        assertLiteralValue("1", folder.handle(new Modulo(new Literal("7"), new Literal("3"))), "modulo should fold");
        assertLiteralValue("8", folder.handle(new Exponentiation(new Literal("2"), new Literal("3"))), "exponentiation should fold");
        assertLiteralValue("1", folder.handle(new Equality(new Literal("2"), new Literal("2"))), "equality should fold");
        assertLiteralValue("1", folder.handle(new Inequality(new Literal("2"), new Literal("3"))), "inequality should fold");
        assertLiteralValue("1", folder.handle(new LessThan(new Literal("2"), new Literal("3"))), "less-than should fold");
        assertLiteralValue("1", folder.handle(new GreaterThan(new Literal("3"), new Literal("2"))), "greater-than should fold");
        assertLiteralValue("1", folder.handle(new LessThanOrEqual(new Literal("2"), new Literal("2"))), "less-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(new GreaterThanOrEqual(new Literal("3"), new Literal("3"))), "greater-than-or-equal should fold");
        assertLiteralValue("1", folder.handle(new Conjunction(new Literal("1"), new Literal("1"))), "conjunction should fold");
        assertLiteralValue("1", folder.handle(new Disjunction(new Literal("0"), new Literal("1"))), "disjunction should fold");
        assertLiteralValue("1", folder.handle(new LogicalNot(new Literal("0"))), "logical-not should fold");
        assertLiteralValue("4", folder.handle(new Conditional(new Literal("1"), new Literal("4"), new Literal("5"))), "conditional should fold");

        var functionCall = folder.handle(new FunctionCall(new VariableReference("sum"), new Addition(new Literal("1"), new Literal("2")), new Literal("4")));
        var call = assertInstanceOf(FunctionCall.class, functionCall, "function call should remain a FunctionCall");
        assertVariableReferenceName("sum", call.callee, "function call callee should remain a variable reference");
        assertLiteralValue("3", call.arguments[0], "function call arguments should be folded");
        assertLiteralValue("4", call.arguments[1], "function call arguments should be preserved");
    }

    private void assertLiteralValue(String expected, visitor.expression.Expression expression, String message) {
        var literal = assertInstanceOf(Literal.class, expression, message);
        org.junit.jupiter.api.Assertions.assertEquals(expected, literal.value, message);
    }

    private void assertVariableReferenceName(String expected, visitor.expression.Expression expression, String message) {
        var variable = assertInstanceOf(VariableReference.class, expression, message);
        org.junit.jupiter.api.Assertions.assertEquals(expected, variable.name, message);
    }
}