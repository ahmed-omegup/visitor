package lib.visitors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lib.expression.*;

public class VariablePathCollector extends AbstractExpressionFunction<Map<String, List<String>>> {
    VariablePathCollector() {}
    private String path;
    private Map<String, List<String>> paths;

    public Map<String, List<String>> apply(Expression expression) {
        var paths = new LinkedHashMap<String, List<String>>();
        collect(expression, "root", paths);
        return paths;
    }
    private void collect(Expression expression, String path, Map<String, List<String>> paths) {
        String previousPath = this.path;
        this.path = path;
        Map<String, List<String>> previousPaths = this.paths;
        this.paths = paths;
        visitExpression(expression);
        this.paths = previousPaths;
        this.path = previousPath;
    }

    public Map<String, List<String>> visit(Literal expression) { return null; }
    public Map<String, List<String>> visit(VariableReference expression) { paths.computeIfAbsent(expression.name, ignored -> new ArrayList<>()).add(path); return null; }
    public Map<String, List<String>> visit(Addition expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Subtraction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Multiplication expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Division expression) { collect(expression.dividend, path + ".dividend", paths); collect(expression.divisor, path + ".divisor", paths); return null; }
    public Map<String, List<String>> visit(Negation expression) { collect(expression.operand, path + ".operand", paths); return null; }
    public Map<String, List<String>> visit(Modulo expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Exponentiation expression) { collect(expression.base, path + ".base", paths); collect(expression.exponent, path + ".exponent", paths); return null; }
    public Map<String, List<String>> visit(Equality expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Inequality expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(LessThan expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(GreaterThan expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(LessThanOrEqual expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(GreaterThanOrEqual expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Conjunction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Disjunction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(LogicalNot expression) { collect(expression.operand, path + ".operand", paths); return null; }
    public Map<String, List<String>> visit(Conditional expression) { collect(expression.condition, path + ".condition", paths); collect(expression.whenTrue, path + ".whenTrue", paths); collect(expression.whenFalse, path + ".whenFalse", paths); return null; }
    public Map<String, List<String>> visit(FunctionCall expression) { collect(expression.callee, path + ".callee", paths);
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            collect(iter.next(), path + ".arguments[" + index + "]", paths);
        }
        return null;
    }

}