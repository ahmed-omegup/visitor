package lib.handlers;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class FunctionNameCollector implements Visitor<Set<String>> {
    FunctionNameCollector() {}

    private boolean active;
    private Set<String> names;

    public Set<String> handle(Expression expression) {
        var names = new LinkedHashSet<String>();
        collect(expression, names);
        return names;
    }
    private void collect(Expression expression, Set<String> names) {
        boolean previousActive = this.active;
        this.active = true;
        Set<String> previousNames = this.names;
        this.names = names;
        expression.accept(this);
        this.names = previousNames;
        this.active = previousActive;
    }

    public Set<String> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public Set<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public Set<String> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, names); collect(expression.divisor, names); return null; }
    public Set<String> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, names); return null; }
    public Set<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, names); collect(expression.exponent, names); return null; }
    public Set<String> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, names); return null; }
    public Set<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, names); collect(expression.whenTrue, names); collect(expression.whenFalse, names); return null; }
    public Set<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        expression.callee.accept(new Visitor<Void>() {
            public Set<String> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } names.add(expression.name); return null; }
            public Set<String> visit(Addition expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Division expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Negation expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Equality expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } return null; }
            public Set<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); } return null; }
        });
        collect(expression.callee, names);
        for (var argument : expression.arguments) {
            collect(argument, names);
        }
        return null;
    }

}