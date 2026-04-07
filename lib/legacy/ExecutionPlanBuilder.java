package lib.handlers;

import lib.expression.*;

public class ExecutionPlanBuilder extends AbstractExpressionFunction<String> {
    ExecutionPlanBuilder() {}
    private StringBuilder builder;
    private int[] counter;
    private int depth;

    public String apply(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, new int[] { 1 }, 0);
        return builder.toString();
    }
    private void append(Expression expression, StringBuilder builder, int[] counter, int depth) {
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        int[] previousCounter = this.counter;
        this.counter = counter;
        int previousDepth = this.depth;
        this.depth = depth;
        visitExpression(expression);
        this.depth = previousDepth;
        this.counter = previousCounter;
        this.builder = previousBuilder;
    }

    private void line(String label) {
        builder.append("  ".repeat(depth)).append(counter[0]++).append(". inspect ").append(label).append('\n');
    }

    public String visit(Literal expression) { line("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { line("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { line("Addition"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(Subtraction expression) { line("Subtraction"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(Multiplication expression) { line("Multiplication"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(Division expression) { line("Division"); append(expression.dividend, builder, counter, depth + 1); append(expression.divisor, builder, counter, depth + 1); return null; }
    public String visit(Negation expression) { line("Negation"); append(expression.operand, builder, counter, depth + 1); return null; }
    public String visit(Modulo expression) { line("Modulo"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(Exponentiation expression) { line("Exponentiation"); append(expression.base, builder, counter, depth + 1); append(expression.exponent, builder, counter, depth + 1); return null; }
    public String visit(Equality expression) { line("Equality"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(Inequality expression) { line("Inequality"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(LessThan expression) { line("LessThan"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(GreaterThan expression) { line("GreaterThan"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(LessThanOrEqual expression) { line("LessThanOrEqual"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(GreaterThanOrEqual expression) { line("GreaterThanOrEqual"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(Conjunction expression) { line("Conjunction"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(Disjunction expression) { line("Disjunction"); append(expression.left, builder, counter, depth + 1); append(expression.right, builder, counter, depth + 1); return null; }
    public String visit(LogicalNot expression) { line("LogicalNot"); append(expression.operand, builder, counter, depth + 1); return null; }
    public String visit(Conditional expression) { line("Conditional"); append(expression.condition, builder, counter, depth + 1); append(expression.whenTrue, builder, counter, depth + 1); append(expression.whenFalse, builder, counter, depth + 1); return null; }
    public String visit(FunctionCall expression) { line("FunctionCall");
        append(expression.callee, builder, counter, depth + 1);
        for (var argument : expression.arguments) {
            append(argument, builder, counter, depth + 1);
        }
        return null;
    }

}