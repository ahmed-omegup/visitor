package lib.visitors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

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

    private void push(String label, Expression... children) {
        labels.add(label);
        for (var child : children) {
            queue.addLast(child);
        }
    }

    public String visit(Literal expression) { push("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { push("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { push("Addition", expression.left, expression.right); return null; }
    public String visit(Subtraction expression) { push("Subtraction", expression.left, expression.right); return null; }
    public String visit(Multiplication expression) { push("Multiplication", expression.left, expression.right); return null; }
    public String visit(Division expression) { push("Division", expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) { push("Negation", expression.operand); return null; }
    public String visit(Modulo expression) { push("Modulo", expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) { push("Exponentiation", expression.base, expression.exponent); return null; }
    public String visit(Equality expression) { push("Equality", expression.left, expression.right); return null; }
    public String visit(Inequality expression) { push("Inequality", expression.left, expression.right); return null; }
    public String visit(LessThan expression) { push("LessThan", expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) { push("GreaterThan", expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) { push("LessThanOrEqual", expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { push("GreaterThanOrEqual", expression.left, expression.right); return null; }
    public String visit(Conjunction expression) { push("Conjunction", expression.left, expression.right); return null; }
    public String visit(Disjunction expression) { push("Disjunction", expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) { push("LogicalNot", expression.operand); return null; }
    public String visit(Conditional expression) { push("Conditional", expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public String visit(FunctionCall expression) { var children = new Expression[expression.arguments.length + 1];
        children[0] = expression.callee;
        System.arraycopy(expression.arguments, 0, children, 1, expression.arguments.length);
        push("FunctionCall", children);
        return null;
    }
}