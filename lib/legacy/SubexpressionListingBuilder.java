package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class SubexpressionListingBuilder extends AbstractExpressionFunction<List<String>> {
    SubexpressionListingBuilder() {}
    private List<String> expressions;
    private String current;

    public List<String> apply(Expression expression) {
        var expressions = new ArrayList<String>();
        collect(expression, expressions);
        return expressions;
    }

    private String collect(Expression expression, List<String> expressions) {
        List<String> previousExpressions = this.expressions;
        String previousCurrent = this.current;
        this.expressions = expressions;
        visitExpression(expression);
        String result = this.current;
        this.current = previousCurrent;
        this.expressions = previousExpressions;
        return result;
    }

    public List<String> visit(Literal expression) { current = expression.value;
        expressions.add(current);
        return null;
    }

    public List<String> visit(VariableReference expression) { current = expression.name;
        expressions.add(current);
        return null;
    }

    public List<String> visit(Addition expression) { current = "(" + collect(expression.left, expressions) + " + " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Subtraction expression) { current = "(" + collect(expression.left, expressions) + " - " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Multiplication expression) { current = "(" + collect(expression.left, expressions) + " * " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Division expression) { current = "(" + collect(expression.dividend, expressions) + " / " + collect(expression.divisor, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Negation expression) { current = "(-" + collect(expression.operand, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Modulo expression) { current = "(" + collect(expression.left, expressions) + " % " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Exponentiation expression) { current = "(" + collect(expression.base, expressions) + " ^ " + collect(expression.exponent, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Equality expression) { current = "(" + collect(expression.left, expressions) + " == " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Inequality expression) { current = "(" + collect(expression.left, expressions) + " != " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(LessThan expression) { current = "(" + collect(expression.left, expressions) + " < " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(GreaterThan expression) { current = "(" + collect(expression.left, expressions) + " > " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(LessThanOrEqual expression) { current = "(" + collect(expression.left, expressions) + " <= " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(GreaterThanOrEqual expression) { current = "(" + collect(expression.left, expressions) + " >= " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Conjunction expression) { current = "(" + collect(expression.left, expressions) + " && " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Disjunction expression) { current = "(" + collect(expression.left, expressions) + " || " + collect(expression.right, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(LogicalNot expression) { current = "(!" + collect(expression.operand, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(Conditional expression) { current = "(" + collect(expression.condition, expressions) + " ? " + collect(expression.whenTrue, expressions)
            + " : " + collect(expression.whenFalse, expressions) + ")";
        expressions.add(current);
        return null;
    }

    public List<String> visit(FunctionCall expression) { var builder = new StringBuilder();
        builder.append(collect(expression.callee, expressions)).append('(');
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            if (index > 0) {
                builder.append(", ");
            }
            builder.append(collect(iter.next(), expressions));
        }
        current = builder.append(')').toString();
        expressions.add(current);
        return null;
    }
}