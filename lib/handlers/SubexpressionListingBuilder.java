package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class SubexpressionListingBuilder implements Visitor<String> {
    SubexpressionListingBuilder() {}

    private List<String> expressions;

    public List<String> handle(Expression expression) {
        var expressions = new ArrayList<String>();
        collect(expression, expressions);
        return expressions;
    }
    private String collect(Expression expression, List<String> expressions) {
        List<String> previousExpressions = this.expressions;
        this.expressions = expressions;
        String result = expression.accept(this);
        this.expressions = previousExpressions;
        return result;
    }

    public String visit(Literal expression) {
        expressions.add(expression.value);
        return expression.value;
    }

    public String visit(VariableReference expression) {
        expressions.add(expression.name);
        return expression.name;
    }

    public String visit(Addition expression) {
        var current = "(" + collect(expression.left, expressions) + " + " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Subtraction expression) {
        var current = "(" + collect(expression.left, expressions) + " - " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Multiplication expression) {
        var current = "(" + collect(expression.left, expressions) + " * " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Division expression) {
        var current = "(" + collect(expression.dividend, expressions) + " / " + collect(expression.divisor, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Negation expression) {
        var current = "(-" + collect(expression.operand, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Modulo expression) {
        var current = "(" + collect(expression.left, expressions) + " % " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Exponentiation expression) {
        var current = "(" + collect(expression.base, expressions) + " ^ " + collect(expression.exponent, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Equality expression) {
        var current = "(" + collect(expression.left, expressions) + " == " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Inequality expression) {
        var current = "(" + collect(expression.left, expressions) + " != " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(LessThan expression) {
        var current = "(" + collect(expression.left, expressions) + " < " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(GreaterThan expression) {
        var current = "(" + collect(expression.left, expressions) + " > " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(LessThanOrEqual expression) {
        var current = "(" + collect(expression.left, expressions) + " <= " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(GreaterThanOrEqual expression) {
        var current = "(" + collect(expression.left, expressions) + " >= " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Conjunction expression) {
        var current = "(" + collect(expression.left, expressions) + " && " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Disjunction expression) {
        var current = "(" + collect(expression.left, expressions) + " || " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(LogicalNot expression) {
        var current = "(!" + collect(expression.operand, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(Conditional expression) {
        var current = "(" + collect(expression.condition, expressions) + " ? " + collect(expression.whenTrue, expressions)
            + " : " + collect(expression.whenFalse, expressions) + ")";
        expressions.add(current);
        return current;
    }

    public String visit(FunctionCall expression) {
        var builder = new StringBuilder();
        builder.append(collect(expression.callee, expressions)).append('(');
        for (int index = 0; index < expression.arguments.length; index++) {
            if (index > 0) {
                builder.append(", ");
            }
            builder.append(collect(expression.arguments[index], expressions));
        }
        var current = builder.append(')').toString();
        expressions.add(current);
        return current;
    }

}