package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class NodePathCollector {
    public List<String> handle(Expression expression) {
        var paths = new ArrayList<String>();
        collect(expression, paths, "0");
        return paths;
    }

    private void collect(Expression expression, List<String> paths, String path) {
        expression.accept(new Visitor<Void>() {
            private void add(String type) {
                paths.add(path + ':' + type);
            }

            public Void visit(Literal expression) { add("Literal"); return null; }
            public Void visit(VariableReference expression) { add("VariableReference"); return null; }
            public Void visit(Addition expression) { add("Addition"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(Subtraction expression) { add("Subtraction"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(Multiplication expression) { add("Multiplication"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(Division expression) { add("Division"); collect(expression.dividend, paths, path + ".0"); collect(expression.divisor, paths, path + ".1"); return null; }
            public Void visit(Negation expression) { add("Negation"); collect(expression.operand, paths, path + ".0"); return null; }
            public Void visit(Modulo expression) { add("Modulo"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(Exponentiation expression) { add("Exponentiation"); collect(expression.base, paths, path + ".0"); collect(expression.exponent, paths, path + ".1"); return null; }
            public Void visit(Equality expression) { add("Equality"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(Inequality expression) { add("Inequality"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(LessThan expression) { add("LessThan"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(GreaterThan expression) { add("GreaterThan"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(LessThanOrEqual expression) { add("LessThanOrEqual"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(GreaterThanOrEqual expression) { add("GreaterThanOrEqual"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(Conjunction expression) { add("Conjunction"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(Disjunction expression) { add("Disjunction"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
            public Void visit(LogicalNot expression) { add("LogicalNot"); collect(expression.operand, paths, path + ".0"); return null; }
            public Void visit(Conditional expression) { add("Conditional"); collect(expression.condition, paths, path + ".0"); collect(expression.whenTrue, paths, path + ".1"); collect(expression.whenFalse, paths, path + ".2"); return null; }
            public Void visit(FunctionCall expression) { add("FunctionCall"); collect(expression.callee, paths, path + ".0"); for (int index = 0; index < expression.arguments.length; index++) { collect(expression.arguments[index], paths, path + "." + (index + 1)); } return null; }
        });
    }
}