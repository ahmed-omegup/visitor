package lib.legacy;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class VariableDepthSequenceBuilder extends AbstractExpressionFunction<List<Integer>> {
    VariableDepthSequenceBuilder() {}
    private int depth;
    private List<Integer> depths;

    public List<Integer> apply(Expression expression) {
        var depths = new ArrayList<Integer>();
        collect(expression, 0, depths);
        return depths;
    }
    private void collect(Expression expression, int depth, List<Integer> depths) {
        int previousDepth = this.depth;
        this.depth = depth;
        List<Integer> previousDepths = this.depths;
        this.depths = depths;
        visitExpression(expression);
        this.depths = previousDepths;
        this.depth = previousDepth;
    }

    public List<Integer> visit(Literal expression) { return null; }
    public List<Integer> visit(VariableReference expression) { depths.add(depth); return null; }
    public List<Integer> visit(Addition expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Subtraction expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Multiplication expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Division expression) { collect(expression.dividend, depth + 1, depths); collect(expression.divisor, depth + 1, depths); return null; }
    public List<Integer> visit(Negation expression) { collect(expression.operand, depth + 1, depths); return null; }
    public List<Integer> visit(Modulo expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Exponentiation expression) { collect(expression.base, depth + 1, depths); collect(expression.exponent, depth + 1, depths); return null; }
    public List<Integer> visit(Equality expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Inequality expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(LessThan expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(GreaterThan expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(LessThanOrEqual expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(GreaterThanOrEqual expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Conjunction expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Disjunction expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(LogicalNot expression) { collect(expression.operand, depth + 1, depths); return null; }
    public List<Integer> visit(Conditional expression) { collect(expression.condition, depth + 1, depths); collect(expression.whenTrue, depth + 1, depths); collect(expression.whenFalse, depth + 1, depths); return null; }
    public List<Integer> visit(FunctionCall expression) { collect(expression.callee, depth + 1, depths);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, depths);
        }
        return null;
    }

}