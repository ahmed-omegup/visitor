package lib.handlers;

import lib.expression.*;

public class DeepestNodePathFinder implements Visitor<Result> {
    DeepestNodePathFinder() {}

    private String path;

    public String handle(Expression expression) {
        return find(expression, "0").path;
    }
    private Result find(Expression expression, String path) {
        String previousPath = this.path;
        this.path = path;
        Result result = expression.accept(this);
        this.path = previousPath;
        return result;
    }

    private Result deeper(Result left, Result right) {
        return left.depth >= right.depth ? left : right;
    }

    public Result visit(Literal expression) { return new Result(path, 0); }
    public Result visit(VariableReference expression) { return new Result(path, 0); }
    public Result visit(Addition expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(Subtraction expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(Multiplication expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(Division expression) { return deeper(find(expression.dividend, path + ".0"), find(expression.divisor, path + ".1")); }
    public Result visit(Negation expression) { var child = find(expression.operand, path + ".0"); return new Result(child.path, child.depth + 1); }
    public Result visit(Modulo expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(Exponentiation expression) { return deeper(find(expression.base, path + ".0"), find(expression.exponent, path + ".1")); }
    public Result visit(Equality expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(Inequality expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(LessThan expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(GreaterThan expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(LessThanOrEqual expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(GreaterThanOrEqual expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(Conjunction expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(Disjunction expression) { return deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); }
    public Result visit(LogicalNot expression) { var child = find(expression.operand, path + ".0"); return new Result(child.path, child.depth + 1); }
    public Result visit(Conditional expression) { return deeper(find(expression.condition, path + ".0"), deeper(find(expression.whenTrue, path + ".1"), find(expression.whenFalse, path + ".2"))); }
    public Result visit(FunctionCall expression) {
        var best = find(expression.callee, path + ".0");
        for (int index = 0; index < expression.arguments.length; index++) {
            best = deeper(best, find(expression.arguments[index], path + "." + (index + 1)));
        }
        return new Result(best.path, best.depth + 1);
    }


    private record Result(String path, int depth) {}
}