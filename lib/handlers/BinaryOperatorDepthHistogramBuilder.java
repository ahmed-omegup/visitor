package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class BinaryOperatorDepthHistogramBuilder {
    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, 0, histogram);
        return histogram;
    }

    private void collect(Expression expression, int depth, Map<Integer, Integer> histogram) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Subtraction expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Multiplication expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Division expression) { add(depth, histogram); collect(expression.dividend, depth + 1, histogram); collect(expression.divisor, depth + 1, histogram); return null; }
            public Void visit(Negation expression) { collect(expression.operand, depth + 1, histogram); return null; }
            public Void visit(Modulo expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Exponentiation expression) { add(depth, histogram); collect(expression.base, depth + 1, histogram); collect(expression.exponent, depth + 1, histogram); return null; }
            public Void visit(Equality expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Inequality expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(LessThan expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(GreaterThan expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(LessThanOrEqual expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(GreaterThanOrEqual expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Conjunction expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Disjunction expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, depth + 1, histogram); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, depth + 1, histogram); collect(expression.whenTrue, depth + 1, histogram); collect(expression.whenFalse, depth + 1, histogram); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, depth + 1, histogram);
                for (var argument : expression.arguments) {
                    collect(argument, depth + 1, histogram);
                }
                return null;
            }
        });
    }

    private void add(int depth, Map<Integer, Integer> histogram) {
        histogram.put(depth, histogram.getOrDefault(depth, 0) + 1);
    }
}