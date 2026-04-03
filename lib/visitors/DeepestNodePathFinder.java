package lib.visitors;

import lib.expression.*;

public class DeepestNodePathFinder implements Visitor<String> {
    DeepestNodePathFinder() {}

    private boolean active;
    private String path;
    private Result result;

    public String handle(Expression expression) {
        return find(expression, "0").path;
    }

    private Result find(Expression expression, String path) {
        boolean previousActive = this.active;
        String previousPath = this.path;
        Result previousResult = this.result;
        this.active = true;
        this.path = path;
        expression.accept(this);
        Result found = this.result;
        this.result = previousResult;
        this.path = previousPath;
        this.active = previousActive;
        return found;
    }

    private Result deeper(Result left, Result right) {
        return left.depth >= right.depth ? left : right;
    }

    public String visit(Literal expression) { if (!active) { return handle(expression); } result = new Result(path, 0); return result.path; }
    public String visit(VariableReference expression) { if (!active) { return handle(expression); } result = new Result(path, 0); return result.path; }
    public String visit(Addition expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Subtraction expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Multiplication expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Division expression) { if (!active) { return handle(expression); } result = deeper(find(expression.dividend, path + ".0"), find(expression.divisor, path + ".1")); return result.path; }
    public String visit(Negation expression) { if (!active) { return handle(expression); } var child = find(expression.operand, path + ".0"); result = new Result(child.path, child.depth + 1); return result.path; }
    public String visit(Modulo expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Exponentiation expression) { if (!active) { return handle(expression); } result = deeper(find(expression.base, path + ".0"), find(expression.exponent, path + ".1")); return result.path; }
    public String visit(Equality expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Inequality expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(LessThan expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(GreaterThan expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(LessThanOrEqual expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(GreaterThanOrEqual expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Conjunction expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Disjunction expression) { if (!active) { return handle(expression); } result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(LogicalNot expression) { if (!active) { return handle(expression); } var child = find(expression.operand, path + ".0"); result = new Result(child.path, child.depth + 1); return result.path; }

    public String visit(Conditional expression) {
        if (!active) {
            return handle(expression);
        }
        result = deeper(find(expression.condition, path + ".0"), deeper(find(expression.whenTrue, path + ".1"), find(expression.whenFalse, path + ".2")));
        return result.path;
    }

    public String visit(FunctionCall expression) {
        if (!active) {
            return handle(expression);
        }
        var best = find(expression.callee, path + ".0");
        for (int index = 0; index < expression.arguments.length; index++) {
            best = deeper(best, find(expression.arguments[index], path + "." + (index + 1)));
        }
        result = new Result(best.path, best.depth + 1);
        return result.path;
    }

    private record Result(String path, int depth) {}
}