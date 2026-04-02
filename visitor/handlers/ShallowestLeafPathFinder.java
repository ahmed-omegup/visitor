package visitor.handlers;

import visitor.expression.*;

public class ShallowestLeafPathFinder {
    public String handle(Expression expression) {
        return find(expression, "root", 0).path;
    }

    private Result find(Expression expression, String path, int depth) {
        return expression.accept(new Visitor<Result>() {
            public Result visit(Literal expression) { return new Result(path, depth); }
            public Result visit(VariableReference expression) { return new Result(path, depth); }
            public Result visit(Addition expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(Subtraction expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(Multiplication expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(Division expression) { return earlier(find(expression.dividend, path + ".dividend", depth + 1), find(expression.divisor, path + ".divisor", depth + 1)); }
            public Result visit(Negation expression) { return find(expression.operand, path + ".operand", depth + 1); }
            public Result visit(Modulo expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(Exponentiation expression) { return earlier(find(expression.base, path + ".base", depth + 1), find(expression.exponent, path + ".exponent", depth + 1)); }
            public Result visit(Equality expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(Inequality expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(LessThan expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(GreaterThan expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(LessThanOrEqual expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(GreaterThanOrEqual expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(Conjunction expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(Disjunction expression) { return earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); }
            public Result visit(LogicalNot expression) { return find(expression.operand, path + ".operand", depth + 1); }
            public Result visit(Conditional expression) {
                return earlier(
                    find(expression.condition, path + ".condition", depth + 1),
                    earlier(find(expression.whenTrue, path + ".whenTrue", depth + 1), find(expression.whenFalse, path + ".whenFalse", depth + 1))
                );
            }
            public Result visit(FunctionCall expression) {
                var best = find(expression.callee, path + ".callee", depth + 1);
                for (int index = 0; index < expression.arguments.length; index++) {
                    best = earlier(best, find(expression.arguments[index], path + ".arguments[" + index + "]", depth + 1));
                }
                return best;
            }
        });
    }

    private Result earlier(Result left, Result right) {
        return left.depth <= right.depth ? left : right;
    }

    private static final class Result {
        private final String path;
        private final int depth;

        private Result(String path, int depth) {
            this.path = path;
            this.depth = depth;
        }
    }
}