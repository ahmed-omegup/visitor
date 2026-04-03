package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class RootToLeafTracePrinter implements Visitor<String> {
    RootToLeafTracePrinter() {}

    private boolean active;
    private List<String> prefix;
    private List<String> traces;

    public String handle(Expression expression) {
        var traces = new ArrayList<String>();
        collect(expression, new ArrayList<>(), traces);
        return String.join("\n", traces);
    }
    private void collect(Expression expression, List<String> prefix, List<String> traces) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousPrefix = this.prefix;
        this.prefix = prefix;
        List<String> previousTraces = this.traces;
        this.traces = traces;
        expression.accept(this);
        this.traces = previousTraces;
        this.prefix = previousPrefix;
        this.active = previousActive;
    }

    public String visit(Literal expression) {
            if (!active) { return handle(expression); }
        var path = new ArrayList<>(prefix);
        path.add("Literal(" + expression.value + ")");
        traces.add(String.join(" -> ", path));
        return null;
    }
    public String visit(VariableReference expression) {
            if (!active) { return handle(expression); }
        var path = new ArrayList<>(prefix);
        path.add("VariableReference(" + expression.name + ")");
        traces.add(String.join(" -> ", path));
        return null;
    }
    public String visit(Addition expression) {
            if (!active) { return handle(expression); } descend("Addition", expression.left, expression.right); return null; }
    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); } descend("Subtraction", expression.left, expression.right); return null; }
    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); } descend("Multiplication", expression.left, expression.right); return null; }
    public String visit(Division expression) {
            if (!active) { return handle(expression); } descend("Division", expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) {
            if (!active) { return handle(expression); } descendUnary("Negation", expression.operand); return null; }
    public String visit(Modulo expression) {
            if (!active) { return handle(expression); } descend("Modulo", expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); } descend("Exponentiation", expression.base, expression.exponent); return null; }
    public String visit(Equality expression) {
            if (!active) { return handle(expression); } descend("Equality", expression.left, expression.right); return null; }
    public String visit(Inequality expression) {
            if (!active) { return handle(expression); } descend("Inequality", expression.left, expression.right); return null; }
    public String visit(LessThan expression) {
            if (!active) { return handle(expression); } descend("LessThan", expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); } descend("GreaterThan", expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } descend("LessThanOrEqual", expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } descend("GreaterThanOrEqual", expression.left, expression.right); return null; }
    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); } descend("Conjunction", expression.left, expression.right); return null; }
    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); } descend("Disjunction", expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); } descendUnary("LogicalNot", expression.operand); return null; }
    public String visit(Conditional expression) {
            if (!active) { return handle(expression); }
        var next = new ArrayList<>(prefix);
        next.add("Conditional");
        collect(expression.condition, next, traces);
        collect(expression.whenTrue, next, traces);
        collect(expression.whenFalse, next, traces);
        return null;
    }
    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        var next = new ArrayList<>(prefix);
        next.add("FunctionCall");
        collect(expression.callee, next, traces);
        for (var argument : expression.arguments) {
            collect(argument, next, traces);
        }
        return null;
    }

    private void descend(String label, Expression left, Expression right) {
        var next = new ArrayList<>(prefix);
        next.add(label);
        collect(left, next, traces);
        collect(right, next, traces);
    }

    private void descendUnary(String label, Expression operand) {
        var next = new ArrayList<>(prefix);
        next.add(label);
        collect(operand, next, traces);
    }

}