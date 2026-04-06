package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import lib.expression.*;

public class ArityHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {
    ArityHistogramBuilder() {}

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        expression.accept(new RecursiveExpressionVisitor(new ArityHistogramBuilderVisitor(histogram)));
        return histogram;
    }
}

final class ArityHistogramBuilderVisitor extends EmptyVisitor {
    private final Map<Integer, Integer> histogram;

    ArityHistogramBuilderVisitor(Map<Integer, Integer> histogram) {
        this.histogram = histogram;
    }

    public Void visit(FunctionCall expression) { histogram.merge(expression.arguments.size(), 1, Integer::sum); return null; }
}