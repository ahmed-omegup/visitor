package lib.visitors;

import lib.expression.*;

public class ShallowestLeafPathFinder implements Visitor<String> {
    ShallowestLeafPathFinder() {}

    private boolean active;
    private String path;
    private int depth;
    private Result result;

    public String handle(Expression expression) {
        return find(expression, "root", 0).path;
    }

    private Result find(Expression expression, String path, int depth) {
        boolean previousActive = this.active;
        String previousPath = this.path;
        int previousDepth = this.depth;
        Result previousResult = this.result;
        this.active = true;
        this.path = path;
        this.depth = depth;
        expression.accept(this);
        Result found = this.result;
        this.result = previousResult;
        this.depth = previousDepth;
        this.path = previousPath;
        this.active = previousActive;
        return found;
    }

    private Result earlier(Result left, Result right) {
        return left.depth <= right.depth ? left : right;
    }

    public String visit(Literal expression) { if (!active) { return handle(expression); } result = new Result(path, depth); return result.path; }
    public String visit(VariableReference expression) { if (!active) { return handle(expression); } result = new Result(path, depth); return result.path; }
    public String visit(Addition expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(Subtraction expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(Multiplication expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(Division expression) { if (!active) { return handle(expression); } result = earlier(find(expression.dividend, path + ".dividend", depth + 1), find(expression.divisor, path + ".divisor", depth + 1)); return result.path; }
    public String visit(Negation expression) { if (!active) { return handle(expression); } result = find(expression.operand, path + ".operand", depth + 1); return result.path; }
    public String visit(Modulo expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(Exponentiation expression) { if (!active) { return handle(expression); } result = earlier(find(expression.base, path + ".base", depth + 1), find(expression.exponent, path + ".exponent", depth + 1)); return result.path; }
    public String visit(Equality expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(Inequality expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(LessThan expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(GreaterThan expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(LessThanOrEqual expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(GreaterThanOrEqual expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(Conjunction expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(Disjunction expression) { if (!active) { return handle(expression); } result = earlier(find(expression.left, path + ".left", depth + 1), find(expression.right, path + ".right", depth + 1)); return result.path; }
    public String visit(LogicalNot expression) { if (!active) { return handle(expression); } result = find(expression.operand, path + ".operand", depth + 1); return result.path; }

    public String visit(Conditional expression) {
        if (!active) {
            return handle(expression);
        }
        result = earlier(
            find(expression.condition, path + ".condition", depth + 1),
            earlier(find(expression.whenTrue, path + ".whenTrue", depth + 1), find(expression.whenFalse, path + ".whenFalse", depth + 1))
        );
        return result.path;
    }

    public String visit(FunctionCall expression) {
        if (!active) {
            return handle(expression);
        }
        var best = find(expression.callee, path + ".callee", depth + 1);
        for (int index = 0; index < expression.arguments.length; index++) {
            best = earlier(best, find(expression.arguments[index], path + ".arguments[" + index + "]", depth + 1));
        }
        result = best;
        return result.path;
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