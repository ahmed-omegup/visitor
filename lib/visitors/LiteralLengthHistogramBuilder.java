package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import lib.expression.*;

public class LiteralLengthHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {
    LiteralLengthHistogramBuilder() {}

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        expression.accept(new RecursiveExpressionVisitor(new LiteralLengthHistogramBuilderVisitor(histogram)));
        return histogram;
    }
}

final class LiteralLengthHistogramBuilderVisitor extends EmptyVisitor {
    private final Map<Integer, Integer> histogram;

    LiteralLengthHistogramBuilderVisitor(Map<Integer, Integer> histogram) {
        this.histogram = histogram;
    }

    public Void visit(Literal expression) {
        histogram.put(expression.value.length(), histogram.getOrDefault(expression.value.length(), 0) + 1);
        return null;
    }
}