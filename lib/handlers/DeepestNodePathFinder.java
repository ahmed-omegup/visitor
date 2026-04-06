package lib.handlers;

import lib.expression.*;

public class DeepestNodePathFinder extends AbstractExpressionFunction<String> {
    DeepestNodePathFinder() {}
    private String path;
    private Result result;

    public String apply(Expression expression) {
        return find(expression, "0").path;
    }

    private Result find(Expression expression, String path) {
        String previousPath = this.path;
        Result previousResult = this.result;
        this.path = path;
        visitExpression(expression);
        Result found = this.result;
        this.result = previousResult;
        this.path = previousPath;
        return found;
    }

    private Result deeper(Result left, Result right) {
        return left.depth >= right.depth ? left : right;
    }

    public String visit(Literal expression) { result = new Result(path, 0); return result.path; }
    public String visit(VariableReference expression) { result = new Result(path, 0); return result.path; }
    public String visit(Addition expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Subtraction expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Multiplication expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Division expression) { result = deeper(find(expression.dividend, path + ".0"), find(expression.divisor, path + ".1")); return result.path; }
    public String visit(Negation expression) { var child = find(expression.operand, path + ".0"); result = new Result(child.path, child.depth + 1); return result.path; }
    public String visit(Modulo expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Exponentiation expression) { result = deeper(find(expression.base, path + ".0"), find(expression.exponent, path + ".1")); return result.path; }
    public String visit(Equality expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Inequality expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(LessThan expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(GreaterThan expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(LessThanOrEqual expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(GreaterThanOrEqual expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Conjunction expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(Disjunction expression) { result = deeper(find(expression.left, path + ".0"), find(expression.right, path + ".1")); return result.path; }
    public String visit(LogicalNot expression) { var child = find(expression.operand, path + ".0"); result = new Result(child.path, child.depth + 1); return result.path; }

    public String visit(Conditional expression) { result = deeper(find(expression.condition, path + ".0"), deeper(find(expression.whenTrue, path + ".1"), find(expression.whenFalse, path + ".2")));
        return result.path;
    }

    public String visit(FunctionCall expression) { var best = find(expression.callee, path + ".0");
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            best = deeper(best, find(iter.next(), path + "." + (index + 1)));
        }
        result = new Result(best.path, best.depth + 1);
        return result.path;
    }

    private record Result(String path, int depth) {}
}