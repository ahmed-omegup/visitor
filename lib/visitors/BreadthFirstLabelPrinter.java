package lib.visitors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;

import lib.expression.*;

public class BreadthFirstLabelPrinter extends AbstractExpressionFunction<String> {
    BreadthFirstLabelPrinter() {}
    private ArrayDeque<Expression> queue;
    private List<String> labels;

    public String apply(Expression expression) {
        ArrayDeque<Expression> previousQueue = this.queue;
        List<String> previousLabels = this.labels;
        this.queue = new ArrayDeque<>();
        this.labels = new ArrayList<>();
        this.queue.add(expression);

        while (!queue.isEmpty()) {
            visitExpression(queue.removeFirst());
        }

        String result = String.join(" | ", labels);
        this.labels = previousLabels;
        this.queue = previousQueue;
        return result;
    }

    private void push(String label, List<Expression> children) {
        labels.add(label);
        for (var child : children) {
            queue.addLast(child);
        }
    }

    public String visit(Literal expression) { push("Literal(" + expression.value + ")", of()); return null; }
    public String visit(VariableReference expression) { push("VariableReference(" + expression.name + ")", of()); return null; }
    public String visit(Addition expression) { push("Addition", of(expression.left, expression.right)); return null; }
    public String visit(Subtraction expression) { push("Subtraction", of(expression.left, expression.right)); return null; }
    public String visit(Multiplication expression) { push("Multiplication", of(expression.left, expression.right)); return null; }
    public String visit(Division expression) { push("Division", of(expression.dividend, expression.divisor)); return null; }
    public String visit(Negation expression) { push("Negation", of(expression.operand)); return null; }
    public String visit(Modulo expression) { push("Modulo", of(expression.left, expression.right)); return null; }
    public String visit(Exponentiation expression) { push("Exponentiation", of(expression.base, expression.exponent)); return null; }
    public String visit(Equality expression) { push("Equality", of(expression.left, expression.right)); return null; }
    public String visit(Inequality expression) { push("Inequality", of(expression.left, expression.right)); return null; }
    public String visit(LessThan expression) { push("LessThan", of(expression.left, expression.right)); return null; }
    public String visit(GreaterThan expression) { push("GreaterThan", of(expression.left, expression.right)); return null; }
    public String visit(LessThanOrEqual expression) { push("LessThanOrEqual", of(expression.left, expression.right)); return null; }
    public String visit(GreaterThanOrEqual expression) { push("GreaterThanOrEqual", of(expression.left, expression.right)); return null; }
    public String visit(Conjunction expression) { push("Conjunction", of(expression.left, expression.right)); return null; }
    public String visit(Disjunction expression) { push("Disjunction", of(expression.left, expression.right)); return null; }
    public String visit(LogicalNot expression) { push("LogicalNot", of(expression.operand)); return null; }
    public String visit(Conditional expression) { push("Conditional", of(expression.condition, expression.whenTrue, expression.whenFalse)); return null; }

    public String visit(FunctionCall expression) { 
        var children = new ArrayList<Expression>(expression.arguments.size() + 1);
        children.add(expression.callee);
        children.addAll(expression.arguments);
        push("FunctionCall", children);
        return null;
    }
}