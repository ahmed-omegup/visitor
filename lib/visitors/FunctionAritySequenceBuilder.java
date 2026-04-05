package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class FunctionAritySequenceBuilder extends AbstractExpressionFunction<List<Integer>> {
    FunctionAritySequenceBuilder() {}
    private List<Integer> arities;

    public List<Integer> apply(Expression expression) {
        var arities = new ArrayList<Integer>();
        collect(expression, arities);
        return arities;
    }
    private void collect(Expression expression, List<Integer> arities) {
        List<Integer> previousArities = this.arities;
        this.arities = arities;
        visitExpression(expression);
        this.arities = previousArities;
    }

    public List<Integer> visit(Literal expression) { return null; }
    public List<Integer> visit(VariableReference expression) { return null; }
    public List<Integer> visit(Addition expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Subtraction expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Multiplication expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Division expression) { collect(expression.dividend, arities); collect(expression.divisor, arities); return null; }
    public List<Integer> visit(Negation expression) { collect(expression.operand, arities); return null; }
    public List<Integer> visit(Modulo expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Exponentiation expression) { collect(expression.base, arities); collect(expression.exponent, arities); return null; }
    public List<Integer> visit(Equality expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Inequality expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(LessThan expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(GreaterThan expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(LessThanOrEqual expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(GreaterThanOrEqual expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Conjunction expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Disjunction expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(LogicalNot expression) { collect(expression.operand, arities); return null; }
    public List<Integer> visit(Conditional expression) { collect(expression.condition, arities); collect(expression.whenTrue, arities); collect(expression.whenFalse, arities); return null; }
    public List<Integer> visit(FunctionCall expression) { arities.add(expression.arguments.size());
        collect(expression.callee, arities);
        for (var argument : expression.arguments) {
            collect(argument, arities);
        }
        return null;
    }

}