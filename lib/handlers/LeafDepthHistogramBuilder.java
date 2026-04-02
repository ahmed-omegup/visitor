package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LeafDepthHistogramBuilder {
    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram, 0);
        return histogram;
    }

    private void collect(Expression expression, Map<Integer, Integer> histogram, int depth) {
        expression.accept(new Visitor<Void>() {
            private void leaf() {
                histogram.merge(depth, 1, Integer::sum);
            }

            public Void visit(Literal expression) { leaf(); return null; }
            public Void visit(VariableReference expression) { leaf(); return null; }
            public Void visit(Addition expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(Division expression) { collect(expression.dividend, histogram, depth + 1); collect(expression.divisor, histogram, depth + 1); return null; }
            public Void visit(Negation expression) { collect(expression.operand, histogram, depth + 1); return null; }
            public Void visit(Modulo expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, histogram, depth + 1); collect(expression.exponent, histogram, depth + 1); return null; }
            public Void visit(Equality expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(Inequality expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(LessThan expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, histogram, depth + 1); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, histogram, depth + 1); collect(expression.whenTrue, histogram, depth + 1); collect(expression.whenFalse, histogram, depth + 1); return null; }
            public Void visit(FunctionCall expression) { collect(expression.callee, histogram, depth + 1); for (var argument : expression.arguments) { collect(argument, histogram, depth + 1); } return null; }
        });
    }
}