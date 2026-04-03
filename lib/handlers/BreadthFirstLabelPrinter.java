package lib.handlers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class BreadthFirstLabelPrinter implements Visitor<String> {
    BreadthFirstLabelPrinter() {}

    private boolean active;
    private ArrayDeque<Expression> queue;
    private List<String> labels;

    public String handle(Expression expression) {
        boolean previousActive = this.active;
        ArrayDeque<Expression> previousQueue = this.queue;
        List<String> previousLabels = this.labels;

        this.active = true;
        this.queue = new ArrayDeque<>();
        this.labels = new ArrayList<>();
        this.queue.add(expression);

        while (!queue.isEmpty()) {
            queue.removeFirst().accept(this);
        }

        String result = String.join(" | ", labels);
        this.labels = previousLabels;
        this.queue = previousQueue;
        this.active = previousActive;
        return result;
    }

    private void push(String label, Expression... children) {
        labels.add(label);
        for (var child : children) {
            queue.addLast(child);
        }
    }

    public String visit(Literal expression) { if (!active) { return handle(expression); } push("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { if (!active) { return handle(expression); } push("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { if (!active) { return handle(expression); } push("Addition", expression.left, expression.right); return null; }
    public String visit(Subtraction expression) { if (!active) { return handle(expression); } push("Subtraction", expression.left, expression.right); return null; }
    public String visit(Multiplication expression) { if (!active) { return handle(expression); } push("Multiplication", expression.left, expression.right); return null; }
    public String visit(Division expression) { if (!active) { return handle(expression); } push("Division", expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) { if (!active) { return handle(expression); } push("Negation", expression.operand); return null; }
    public String visit(Modulo expression) { if (!active) { return handle(expression); } push("Modulo", expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) { if (!active) { return handle(expression); } push("Exponentiation", expression.base, expression.exponent); return null; }
    public String visit(Equality expression) { if (!active) { return handle(expression); } push("Equality", expression.left, expression.right); return null; }
    public String visit(Inequality expression) { if (!active) { return handle(expression); } push("Inequality", expression.left, expression.right); return null; }
    public String visit(LessThan expression) { if (!active) { return handle(expression); } push("LessThan", expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) { if (!active) { return handle(expression); } push("GreaterThan", expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) { if (!active) { return handle(expression); } push("LessThanOrEqual", expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { if (!active) { return handle(expression); } push("GreaterThanOrEqual", expression.left, expression.right); return null; }
    public String visit(Conjunction expression) { if (!active) { return handle(expression); } push("Conjunction", expression.left, expression.right); return null; }
    public String visit(Disjunction expression) { if (!active) { return handle(expression); } push("Disjunction", expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) { if (!active) { return handle(expression); } push("LogicalNot", expression.operand); return null; }
    public String visit(Conditional expression) { if (!active) { return handle(expression); } push("Conditional", expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public String visit(FunctionCall expression) {
        if (!active) {
            return handle(expression);
        }
        var children = new Expression[expression.arguments.length + 1];
        children[0] = expression.callee;
        System.arraycopy(expression.arguments, 0, children, 1, expression.arguments.length);
        push("FunctionCall", children);
        return null;
    }
}