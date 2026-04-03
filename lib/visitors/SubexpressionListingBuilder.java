package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class SubexpressionListingBuilder implements Visitor<List<String>> {
    SubexpressionListingBuilder() {}

    private boolean active;
    private List<String> expressions;
    private String current;

    public List<String> handle(Expression expression) {
        var expressions = new ArrayList<String>();
        collect(expression, expressions);
        return expressions;
    }

    private String collect(Expression expression, List<String> expressions) {
        boolean previousActive = this.active;
        List<String> previousExpressions = this.expressions;
        String previousCurrent = this.current;
        this.active = true;
        this.expressions = expressions;
        expression.accept(this);
        String result = this.current;
        this.current = previousCurrent;
        this.expressions = previousExpressions;
        this.active = previousActive;
        return result;
    }

    public List<String> visit(Literal expression) {
        if (!active) {
            return handle(expression);
        }
        current = expression.value;
        expressions.add(current);
        return null;
    }

    public List<String> visit(VariableReference expression) {
        if (!active) {
            return handle(expression);
        }
        current = expression.name;
        expressions.add(current);
        return null;
    }

    public List<String> visit(Addition expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " + " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Subtraction expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " - " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Multiplication expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " * " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Division expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.dividend, expressions) + " / " + collect(expression.divisor, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Negation expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(-" + collect(expression.operand, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Modulo expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " % " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Exponentiation expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.base, expressions) + " ^ " + collect(expression.exponent, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Equality expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " == " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Inequality expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " != " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(LessThan expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " < " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(GreaterThan expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " > " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(LessThanOrEqual expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " <= " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(GreaterThanOrEqual expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " >= " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Conjunction expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " && " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Disjunction expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.left, expressions) + " || " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(LogicalNot expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(!" + collect(expression.operand, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Conditional expression) {
        if (!active) {
            return handle(expression);
        }
        current = "(" + collect(expression.condition, expressions) + " ? " + collect(expression.whenTrue, expressions)
            + " : " + collect(expression.whenFalse, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(FunctionCall expression) {
        if (!active) {
            return handle(expression);
        }
        var builder = new StringBuilder();
        builder.append(collect(expression.callee, expressions)).append('(');
        for (int index = 0; index < expression.arguments.length; index++) {
            if (index > 0) {
                builder.append(", ");
            }
            builder.append(collect(expression.arguments[index], expressions));
        }
        current = builder.append(')').toString();
        expressions.add(current);
        return null;
    }
}