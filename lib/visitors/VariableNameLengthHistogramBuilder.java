package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import lib.expression.*;

public class VariableNameLengthHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {
    VariableNameLengthHistogramBuilder() {}

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        expression.accept(new RecursiveExpressionVisitor(new VariableNameLengthHistogramBuilderVisitor(histogram)));
        return histogram;
    }
}

final class VariableNameLengthHistogramBuilderVisitor extends EmptyVisitor {
    private final Map<Integer, Integer> histogram;

    VariableNameLengthHistogramBuilderVisitor(Map<Integer, Integer> histogram) {
        this.histogram = histogram;
    }

    public Void visit(VariableReference expression) {
        histogram.put(expression.name.length(), histogram.getOrDefault(expression.name.length(), 0) + 1);
        return null;
    }
}