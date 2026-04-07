package lib.visitors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public final class ExpressionChildren implements Visitor<List<Expression>> {

    public List<Expression> visit(Literal expression) { return List.of(); }
    public List<Expression> visit(VariableReference expression) { return List.of(); }
    public List<Expression> visit(Addition expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(Subtraction expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(Multiplication expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(Division expression) { return List.of(expression.dividend, expression.divisor); }
    public List<Expression> visit(Negation expression) { return List.of(expression.operand); }
    public List<Expression> visit(Modulo expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(Exponentiation expression) { return List.of(expression.base, expression.exponent); }
    public List<Expression> visit(Equality expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(Inequality expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(LessThan expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(GreaterThan expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(LessThanOrEqual expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(GreaterThanOrEqual expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(Conjunction expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(Disjunction expression) { return List.of(expression.left, expression.right); }
    public List<Expression> visit(LogicalNot expression) { return List.of(expression.operand); }
    public List<Expression> visit(Conditional expression) { return List.of(expression.condition, expression.whenTrue, expression.whenFalse); }

    public List<Expression> visit(FunctionCall expression) {
        var children = new Expression[expression.arguments.size() + 1];
        children[0] = expression.callee;
        for (int index = 0; index < expression.arguments.size(); index++) {
            children[index + 1] = expression.arguments.get(index);
        }
        return Arrays.asList(children);
    }
}