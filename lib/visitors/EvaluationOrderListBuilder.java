package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class EvaluationOrderListBuilder implements Visitor<List<String>> {
    EvaluationOrderListBuilder() {}

    private boolean active;
    private List<String> steps;

    public List<String> handle(Expression expression) {
        var steps = new ArrayList<String>();
        append(expression, steps);
        return steps;
    }
    private void append(Expression expression, List<String> steps) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousSteps = this.steps;
        this.steps = steps;
        expression.accept(this);
        this.steps = previousSteps;
        this.active = previousActive;
    }

    private void step(String value) {
        steps.add(value);
    }

    public List<String> visit(Literal expression) {
            if (!active) { return handle(expression); } step("Literal(" + expression.value + ")"); return null; }
    public List<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } step("VariableReference(" + expression.name + ")"); return null; }
    public List<String> visit(Addition expression) {
            if (!active) { return handle(expression); } step("Addition"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } step("Subtraction"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } step("Multiplication"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Division expression) {
            if (!active) { return handle(expression); } step("Division"); append(expression.dividend, steps); append(expression.divisor, steps); return null; }
    public List<String> visit(Negation expression) {
            if (!active) { return handle(expression); } step("Negation"); append(expression.operand, steps); return null; }
    public List<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } step("Modulo"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } step("Exponentiation"); append(expression.base, steps); append(expression.exponent, steps); return null; }
    public List<String> visit(Equality expression) {
            if (!active) { return handle(expression); } step("Equality"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } step("Inequality"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } step("LessThan"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } step("GreaterThan"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } step("LessThanOrEqual"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } step("GreaterThanOrEqual"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } step("Conjunction"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } step("Disjunction"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } step("LogicalNot"); append(expression.operand, steps); return null; }
    public List<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } step("Conditional"); append(expression.condition, steps); append(expression.whenTrue, steps); append(expression.whenFalse, steps); return null; }
    public List<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        step("FunctionCall");
        append(expression.callee, steps);
        for (var argument : expression.arguments) {
            append(argument, steps);
        }
        return null;
    }

}