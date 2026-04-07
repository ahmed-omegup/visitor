package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class FunctionCallPathCollector extends AbstractExpressionFunction<List<String>> {
    FunctionCallPathCollector() {}
    private String path;
    private List<String> paths;

    public List<String> apply(Expression expression) {
        var paths = new ArrayList<String>();
        collect(expression, "root", paths);
        return paths;
    }
    private void collect(Expression expression, String path, List<String> paths) {
        String previousPath = this.path;
        this.path = path;
        List<String> previousPaths = this.paths;
        this.paths = paths;
        visitExpression(expression);
        this.paths = previousPaths;
        this.path = previousPath;
    }

    public List<String> visit(Literal expression) { return null; }
    public List<String> visit(VariableReference expression) { return null; }
    public List<String> visit(Addition expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(Subtraction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(Multiplication expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(Division expression) { collect(expression.dividend, path + ".dividend", paths); collect(expression.divisor, path + ".divisor", paths); return null; }
    public List<String> visit(Negation expression) { collect(expression.operand, path + ".operand", paths); return null; }
    public List<String> visit(Modulo expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(Exponentiation expression) { collect(expression.base, path + ".base", paths); collect(expression.exponent, path + ".exponent", paths); return null; }
    public List<String> visit(Equality expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(Inequality expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(LessThan expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(GreaterThan expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(LessThanOrEqual expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(Conjunction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(Disjunction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public List<String> visit(LogicalNot expression) { collect(expression.operand, path + ".operand", paths); return null; }
    public List<String> visit(Conditional expression) { collect(expression.condition, path + ".condition", paths); collect(expression.whenTrue, path + ".whenTrue", paths); collect(expression.whenFalse, path + ".whenFalse", paths); return null; }
    public List<String> visit(FunctionCall expression) { paths.add(path);
        collect(expression.callee, path + ".callee", paths);
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            collect(iter.next(), path + ".arguments[" + index + "]", paths);
        }
        return null;
    }

}