package lib.legacy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class LiteralLengthHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {
    LiteralLengthHistogramBuilder() {}

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        var handler = new RecursiveExpression(new LiteralLengthHistogramBuilderVisitor(histogram));
        handler.accept(expression);
        return histogram;
    }
}

final class LiteralLengthHistogramBuilderVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final Map<Integer, Integer> histogram;

    LiteralLengthHistogramBuilderVisitor(Map<Integer, Integer> histogram) {
        this.histogram = histogram;
    }

    public Void visit(Literal expression) {
        histogram.put(expression.value.length(), histogram.getOrDefault(expression.value.length(), 0) + 1);
        return null;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }
}