package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class DepthAnnotatedPreorderPrinter implements Visitor<String> {
    DepthAnnotatedPreorderPrinter() {}

    private boolean active;
    private int depth;
    private List<String> lines;

    public String handle(Expression expression) {
        var lines = new ArrayList<String>();
        collect(expression, 0, lines);
        return String.join("\n", lines);
    }
    private void collect(Expression expression, int depth, List<String> lines) {
        boolean previousActive = this.active;
        this.active = true;
        int previousDepth = this.depth;
        this.depth = depth;
        List<String> previousLines = this.lines;
        this.lines = lines;
        expression.accept(this);
        this.lines = previousLines;
        this.depth = previousDepth;
        this.active = previousActive;
    }

    public String visit(Literal expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Addition"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Subtraction"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Multiplication"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Division expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Division"); collect(expression.dividend, depth + 1, lines); collect(expression.divisor, depth + 1, lines); return null; }
    public String visit(Negation expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Negation"); collect(expression.operand, depth + 1, lines); return null; }
    public String visit(Modulo expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Modulo"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Exponentiation"); collect(expression.base, depth + 1, lines); collect(expression.exponent, depth + 1, lines); return null; }
    public String visit(Equality expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Equality"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Inequality expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Inequality"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(LessThan expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": LessThan"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": GreaterThan"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": LessThanOrEqual"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": GreaterThanOrEqual"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Conjunction"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Disjunction"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": LogicalNot"); collect(expression.operand, depth + 1, lines); return null; }
    public String visit(Conditional expression) {
            if (!active) { return handle(expression); } lines.add(depth + ": Conditional"); collect(expression.condition, depth + 1, lines); collect(expression.whenTrue, depth + 1, lines); collect(expression.whenFalse, depth + 1, lines); return null; }
    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        lines.add(depth + ": FunctionCall");
        collect(expression.callee, depth + 1, lines);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, lines);
        }
        return null;
    }

}