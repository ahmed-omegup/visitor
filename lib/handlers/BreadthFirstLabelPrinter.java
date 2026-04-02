package lib.handlers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class BreadthFirstLabelPrinter {
    public String handle(Expression expression) {
        var queue = new ArrayDeque<Expression>();
        var labels = new ArrayList<String>();
        queue.add(expression);

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();
            current.accept(new Visitor<Void>() {
                private void push(String label, Expression... children) {
                    labels.add(label);
                    for (var child : children) {
                        queue.addLast(child);
                    }
                }

                public Void visit(Literal expression) { push("Literal(" + expression.value + ")"); return null; }
                public Void visit(VariableReference expression) { push("VariableReference(" + expression.name + ")"); return null; }
                public Void visit(Addition expression) { push("Addition", expression.left, expression.right); return null; }
                public Void visit(Subtraction expression) { push("Subtraction", expression.left, expression.right); return null; }
                public Void visit(Multiplication expression) { push("Multiplication", expression.left, expression.right); return null; }
                public Void visit(Division expression) { push("Division", expression.dividend, expression.divisor); return null; }
                public Void visit(Negation expression) { push("Negation", expression.operand); return null; }
                public Void visit(Modulo expression) { push("Modulo", expression.left, expression.right); return null; }
                public Void visit(Exponentiation expression) { push("Exponentiation", expression.base, expression.exponent); return null; }
                public Void visit(Equality expression) { push("Equality", expression.left, expression.right); return null; }
                public Void visit(Inequality expression) { push("Inequality", expression.left, expression.right); return null; }
                public Void visit(LessThan expression) { push("LessThan", expression.left, expression.right); return null; }
                public Void visit(GreaterThan expression) { push("GreaterThan", expression.left, expression.right); return null; }
                public Void visit(LessThanOrEqual expression) { push("LessThanOrEqual", expression.left, expression.right); return null; }
                public Void visit(GreaterThanOrEqual expression) { push("GreaterThanOrEqual", expression.left, expression.right); return null; }
                public Void visit(Conjunction expression) { push("Conjunction", expression.left, expression.right); return null; }
                public Void visit(Disjunction expression) { push("Disjunction", expression.left, expression.right); return null; }
                public Void visit(LogicalNot expression) { push("LogicalNot", expression.operand); return null; }
                public Void visit(Conditional expression) { push("Conditional", expression.condition, expression.whenTrue, expression.whenFalse); return null; }
                public Void visit(FunctionCall expression) {
                    var children = new Expression[expression.arguments.length + 1];
                    children[0] = expression.callee;
                    System.arraycopy(expression.arguments, 0, children, 1, expression.arguments.length);
                    push("FunctionCall", children);
                    return null;
                }
            });
        }

        return String.join(" | ", labels);
    }
}