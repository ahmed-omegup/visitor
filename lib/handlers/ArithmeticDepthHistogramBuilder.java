package lib.handlers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.ExpressionVisitor;
import lib.visitors.ExpressionChildren;

public final class ArithmeticDepthHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {
    private static final Set<String> ARITHMETIC_TYPES = Set.of(
            "Addition",
            "Subtraction",
            "Multiplication",
            "Division",
            "Negation",
            "Modulo",
            "Exponentiation");

    private final Function<Expression, String> typeNames = new ExpressionClassNameExtractor();
    private final ExpressionVisitor<List<Expression>> children = new ExpressionChildren();

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, 0, histogram);
        return histogram;
    }

    private void collect(Expression expression, int depth, Map<Integer, Integer> histogram) {
        if (ARITHMETIC_TYPES.contains(typeNames.apply(expression))) {
            histogram.put(depth, histogram.getOrDefault(depth, 0) + 1);
        }
        for (var child : expression.accept(children)) {
            collect(child, depth + 1, histogram);
        }
    }
}