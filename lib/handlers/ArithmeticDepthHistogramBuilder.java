package lib.handlers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import lib.expression.*;
import lib.expression.category.*;
import lib.visitors.CategoryVisitor;
import lib.visitors.ExpressionChildren;

public final class ArithmeticDepthHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {

    private final Function<Expression, String> typeNames = new ExpressionClassNameExtractor();
    private final ExpressionVisitor<List<Expression>> children = new ExpressionChildren();
    private final Function<Expression, CategoryExpression> cat = new CategoryVisitor();

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, 0, histogram);
        return histogram;
    }

    private void collect(Expression expression, int depth, Map<Integer, Integer> histogram) {
        Integer diff = cat.apply(expression).accept(new CategoryExpressionVisitor<>() {
            public Integer visit(FunctionCall e) {
                return 0;
            };
            public Integer visit(LogicalExpression e) {
                return 0;
            };
            public Integer visit(ComparisonExpression e) {
                return 0;
            };
            public Integer visit(LeafExpression e) {
                return 0;
            };
            public Integer visit(ArithmeticExpression e) {
                return 1;
            };
        });
        if (diff > 0) histogram.put(depth, histogram.getOrDefault(depth, 0) + diff);
        for (var child : expression.accept(children)) {
            collect(child, depth + 1, histogram);
        }
    }
}