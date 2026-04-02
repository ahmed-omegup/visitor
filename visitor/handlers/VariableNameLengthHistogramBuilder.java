package visitor.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import visitor.expression.*;

public class VariableNameLengthHistogramBuilder {
    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram);
        return histogram;
    }

    private void collect(Expression expression, Map<Integer, Integer> histogram) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { histogram.put(expression.name.length(), histogram.getOrDefault(expression.name.length(), 0) + 1); return null; }
            public Void visit(Addition expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Division expression) { collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
            public Void visit(Negation expression) { collect(expression.operand, histogram); return null; }
            public Void visit(Modulo expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
            public Void visit(Equality expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Inequality expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LessThan expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, histogram); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, histogram);
                for (var argument : expression.arguments) {
                    collect(argument, histogram);
                }
                return null;
            }
        });
    }
}