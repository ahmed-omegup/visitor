package visitor.handlers;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;

import visitor.expression.*;

public class LevelWidthHistogramBuilder {
    public Map<Integer, Integer> handle(Expression expression) {
        var queue = new ArrayDeque<Expression>();
        var depths = new ArrayDeque<Integer>();
        var histogram = new LinkedHashMap<Integer, Integer>();
        queue.add(expression);
        depths.add(0);

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();
            var depth = depths.removeFirst();
            histogram.merge(depth, 1, Integer::sum);
            current.accept(new Visitor<Void>() {
                private void push(Expression... children) {
                    for (var child : children) {
                        queue.addLast(child);
                        depths.addLast(depth + 1);
                    }
                }

                public Void visit(Literal expression) { return null; }
                public Void visit(VariableReference expression) { return null; }
                public Void visit(Addition expression) { push(expression.left, expression.right); return null; }
                public Void visit(Subtraction expression) { push(expression.left, expression.right); return null; }
                public Void visit(Multiplication expression) { push(expression.left, expression.right); return null; }
                public Void visit(Division expression) { push(expression.dividend, expression.divisor); return null; }
                public Void visit(Negation expression) { push(expression.operand); return null; }
                public Void visit(Modulo expression) { push(expression.left, expression.right); return null; }
                public Void visit(Exponentiation expression) { push(expression.base, expression.exponent); return null; }
                public Void visit(Equality expression) { push(expression.left, expression.right); return null; }
                public Void visit(Inequality expression) { push(expression.left, expression.right); return null; }
                public Void visit(LessThan expression) { push(expression.left, expression.right); return null; }
                public Void visit(GreaterThan expression) { push(expression.left, expression.right); return null; }
                public Void visit(LessThanOrEqual expression) { push(expression.left, expression.right); return null; }
                public Void visit(GreaterThanOrEqual expression) { push(expression.left, expression.right); return null; }
                public Void visit(Conjunction expression) { push(expression.left, expression.right); return null; }
                public Void visit(Disjunction expression) { push(expression.left, expression.right); return null; }
                public Void visit(LogicalNot expression) { push(expression.operand); return null; }
                public Void visit(Conditional expression) { push(expression.condition, expression.whenTrue, expression.whenFalse); return null; }
                public Void visit(FunctionCall expression) {
                    queue.addLast(expression.callee);
                    depths.addLast(depth + 1);
                    for (var argument : expression.arguments) {
                        queue.addLast(argument);
                        depths.addLast(depth + 1);
                    }
                    return null;
                }
            });
        }

        return histogram;
    }
}