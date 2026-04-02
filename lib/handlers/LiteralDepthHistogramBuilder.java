package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LiteralDepthHistogramBuilder {
    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, 0, histogram);
        return histogram;
    }

    private void collect(Expression expression, int depth, Map<Integer, Integer> histogram) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { histogram.put(depth, histogram.getOrDefault(depth, 0) + 1); return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Division expression) { collect(expression.dividend, depth + 1, histogram); collect(expression.divisor, depth + 1, histogram); return null; }
            public Void visit(Negation expression) { collect(expression.operand, depth + 1, histogram); return null; }
            public Void visit(Modulo expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, depth + 1, histogram); collect(expression.exponent, depth + 1, histogram); return null; }
            public Void visit(Equality expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Inequality expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(LessThan expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
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
}