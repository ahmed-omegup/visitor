package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class BranchingFactorHistogramBuilder {
    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram);
        return histogram;
    }

    private void collect(Expression expression, Map<Integer, Integer> histogram) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { add(0, histogram); return null; }
            public Void visit(VariableReference expression) { add(0, histogram); return null; }
            public Void visit(Addition expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Subtraction expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Multiplication expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Division expression) { add(2, histogram); collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
            public Void visit(Negation expression) { add(1, histogram); collect(expression.operand, histogram); return null; }
            public Void visit(Modulo expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Exponentiation expression) { add(2, histogram); collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
            public Void visit(Equality expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Inequality expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LessThan expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(GreaterThan expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LessThanOrEqual expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(GreaterThanOrEqual expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Conjunction expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Disjunction expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LogicalNot expression) { add(1, histogram); collect(expression.operand, histogram); return null; }
            public Void visit(Conditional expression) { add(3, histogram); collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
            public Void visit(FunctionCall expression) {
                add(expression.arguments.length + 1, histogram);
                collect(expression.callee, histogram);
                for (var argument : expression.arguments) {
                    collect(argument, histogram);
                }
                return null;
            }
        });
    }

    private void add(int branchingFactor, Map<Integer, Integer> histogram) {
        histogram.put(branchingFactor, histogram.getOrDefault(branchingFactor, 0) + 1);
    }
}