package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LiteralLengthHistogramBuilder extends AbstractExpressionFunction<Map<Integer, Integer>> {
    LiteralLengthHistogramBuilder() {}
    private Map<Integer, Integer> histogram;

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram);
        return histogram;
    }
    private void collect(Expression expression, Map<Integer, Integer> histogram) {
        Map<Integer, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        visitExpression(expression);
        this.histogram = previousHistogram;
    }

    public Map<Integer, Integer> visit(Literal expression) { histogram.put(expression.value.length(), histogram.getOrDefault(expression.value.length(), 0) + 1); return null; }
    public Map<Integer, Integer> visit(VariableReference expression) { return null; }
    public Map<Integer, Integer> visit(Addition expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Division expression) { collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
    public Map<Integer, Integer> visit(Negation expression) { collect(expression.operand, histogram); return null; }
    public Map<Integer, Integer> visit(Modulo expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) { collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
    public Map<Integer, Integer> visit(Equality expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Inequality expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LessThan expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) { collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) { collect(expression.operand, histogram); return null; }
    public Map<Integer, Integer> visit(Conditional expression) { collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
    public Map<Integer, Integer> visit(FunctionCall expression) { collect(expression.callee, histogram);
        for (var argument : expression.arguments) {
            collect(argument, histogram);
        }
        return null;
    }

}