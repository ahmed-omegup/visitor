package lib.handlers;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class FunctionNameCollector {
    public Set<String> handle(Expression expression) {
        var names = new LinkedHashSet<String>();
        collect(expression, names);
        return names;
    }

    private void collect(Expression expression, Set<String> names) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Division expression) { collect(expression.dividend, names); collect(expression.divisor, names); return null; }
            public Void visit(Negation expression) { collect(expression.operand, names); return null; }
            public Void visit(Modulo expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, names); collect(expression.exponent, names); return null; }
            public Void visit(Equality expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Inequality expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(LessThan expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, names); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, names); collect(expression.whenTrue, names); collect(expression.whenFalse, names); return null; }
            public Void visit(FunctionCall expression) {
                expression.callee.accept(new Visitor<Void>() {
                    public Void visit(Literal expression) { return null; }
                    public Void visit(VariableReference expression) { names.add(expression.name); return null; }
                    public Void visit(Addition expression) { return null; }
                    public Void visit(Subtraction expression) { return null; }
                    public Void visit(Multiplication expression) { return null; }
                    public Void visit(Division expression) { return null; }
                    public Void visit(Negation expression) { return null; }
                    public Void visit(Modulo expression) { return null; }
                    public Void visit(Exponentiation expression) { return null; }
                    public Void visit(Equality expression) { return null; }
                    public Void visit(Inequality expression) { return null; }
                    public Void visit(LessThan expression) { return null; }
                    public Void visit(GreaterThan expression) { return null; }
                    public Void visit(LessThanOrEqual expression) { return null; }
                    public Void visit(GreaterThanOrEqual expression) { return null; }
                    public Void visit(Conjunction expression) { return null; }
                    public Void visit(Disjunction expression) { return null; }
                    public Void visit(LogicalNot expression) { return null; }
                    public Void visit(Conditional expression) { return null; }
                    public Void visit(FunctionCall expression) { return null; }
                });
                collect(expression.callee, names);
                for (var argument : expression.arguments) {
                    collect(argument, names);
                }
                return null;
            }
        });
    }
}