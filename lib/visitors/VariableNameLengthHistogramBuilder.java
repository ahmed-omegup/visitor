package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class VariableNameLengthHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {
    VariableNameLengthHistogramBuilder() {}

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        var handler = new RecursiveExpression(new VariableNameLengthHistogramBuilderVisitor(histogram));
        handler.accept(expression);
        return histogram;
    }
}

final class VariableNameLengthHistogramBuilderVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final Map<Integer, Integer> histogram;

    VariableNameLengthHistogramBuilderVisitor(Map<Integer, Integer> histogram) {
        this.histogram = histogram;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(VariableReference expression) {
        histogram.put(expression.name.length(), histogram.getOrDefault(expression.name.length(), 0) + 1);
        return null;
    }
}