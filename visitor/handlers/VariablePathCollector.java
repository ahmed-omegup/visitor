package visitor.handlers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import visitor.expression.*;

public class VariablePathCollector {
    public Map<String, List<String>> handle(Expression expression) {
        var paths = new LinkedHashMap<String, List<String>>();
        collect(expression, "root", paths);
        return paths;
    }

    private void collect(Expression expression, String path, Map<String, List<String>> paths) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { paths.computeIfAbsent(expression.name, ignored -> new ArrayList<>()).add(path); return null; }
            public Void visit(Addition expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(Division expression) { collect(expression.dividend, path + ".dividend", paths); collect(expression.divisor, path + ".divisor", paths); return null; }
            public Void visit(Negation expression) { collect(expression.operand, path + ".operand", paths); return null; }
            public Void visit(Modulo expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, path + ".base", paths); collect(expression.exponent, path + ".exponent", paths); return null; }
            public Void visit(Equality expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(Inequality expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(LessThan expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, path + ".operand", paths); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, path + ".condition", paths); collect(expression.whenTrue, path + ".whenTrue", paths); collect(expression.whenFalse, path + ".whenFalse", paths); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, path + ".callee", paths);
                for (int index = 0; index < expression.arguments.length; index++) {
                    collect(expression.arguments[index], path + ".arguments[" + index + "]", paths);
                }
                return null;
            }
        });
    }
}