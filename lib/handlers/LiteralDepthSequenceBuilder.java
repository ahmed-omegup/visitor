package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class LiteralDepthSequenceBuilder implements Visitor<List<Integer>> {
    LiteralDepthSequenceBuilder() {}

    private boolean active;
    private int depth;
    private List<Integer> depths;

    public List<Integer> handle(Expression expression) {
        var depths = new ArrayList<Integer>();
        collect(expression, 0, depths);
        return depths;
    }
    private void collect(Expression expression, int depth, List<Integer> depths) {
        boolean previousActive = this.active;
        this.active = true;
        int previousDepth = this.depth;
        this.depth = depth;
        List<Integer> previousDepths = this.depths;
        this.depths = depths;
        expression.accept(this);
        this.depths = previousDepths;
        this.depth = previousDepth;
        this.active = previousActive;
    }

    public List<Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } depths.add(depth); return null; }
    public List<Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public List<Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, depth + 1, depths); collect(expression.divisor, depth + 1, depths); return null; }
    public List<Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, depth + 1, depths); return null; }
    public List<Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, depth + 1, depths); collect(expression.exponent, depth + 1, depths); return null; }
    public List<Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
    public List<Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, depth + 1, depths); return null; }
    public List<Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, depth + 1, depths); collect(expression.whenTrue, depth + 1, depths); collect(expression.whenFalse, depth + 1, depths); return null; }
    public List<Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        collect(expression.callee, depth + 1, depths);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, depths);
        }
        return null;
    }

}