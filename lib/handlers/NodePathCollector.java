package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class NodePathCollector extends AbstractExpressionFunction<List<String>> {
    NodePathCollector() {
    }

    private List<String> paths;
    private String path;

    public List<String> apply(Expression expression) {
        var paths = new ArrayList<String>();
        collect(expression, paths, "0");
        return paths;
    }

    private void collect(Expression expression, List<String> paths, String path) {
        List<String> previousPaths = this.paths;
        this.paths = paths;
        String previousPath = this.path;
        this.path = path;
        visitExpression(expression);
        this.path = previousPath;
        this.paths = previousPaths;
    }

    private void add(String type) {
        paths.add(path + ':' + type);
    }

    public List<String> visit(Literal expression) {
        add("Literal");
        return null;
    }

    public List<String> visit(VariableReference expression) {
        add("VariableReference");
        return null;
    }

    public List<String> visit(Addition expression) {
        add("Addition");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(Subtraction expression) {
        add("Subtraction");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(Multiplication expression) {
        add("Multiplication");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(Division expression) {
        add("Division");
        collect(expression.dividend, paths, path + ".0");
        collect(expression.divisor, paths, path + ".1");
        return null;
    }

    public List<String> visit(Negation expression) {
        add("Negation");
        collect(expression.operand, paths, path + ".0");
        return null;
    }

    public List<String> visit(Modulo expression) {
        add("Modulo");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(Exponentiation expression) {
        add("Exponentiation");
        collect(expression.base, paths, path + ".0");
        collect(expression.exponent, paths, path + ".1");
        return null;
    }

    public List<String> visit(Equality expression) {
        add("Equality");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(Inequality expression) {
        add("Inequality");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(LessThan expression) {
        add("LessThan");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(GreaterThan expression) {
        add("GreaterThan");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(LessThanOrEqual expression) {
        add("LessThanOrEqual");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(GreaterThanOrEqual expression) {
        add("GreaterThanOrEqual");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(Conjunction expression) {
        add("Conjunction");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(Disjunction expression) {
        add("Disjunction");
        collect(expression.left, paths, path + ".0");
        collect(expression.right, paths, path + ".1");
        return null;
    }

    public List<String> visit(LogicalNot expression) {
        add("LogicalNot");
        collect(expression.operand, paths, path + ".0");
        return null;
    }

    public List<String> visit(Conditional expression) {
        add("Conditional");
        collect(expression.condition, paths, path + ".0");
        collect(expression.whenTrue, paths, path + ".1");
        collect(expression.whenFalse, paths, path + ".2");
        return null;
    }

    public List<String> visit(FunctionCall expression) {
        add("FunctionCall");
        collect(expression.callee, paths, path + ".0");
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            collect(iter.next(), paths, path + "." + (index + 1));
        }
        return null;
    }

}