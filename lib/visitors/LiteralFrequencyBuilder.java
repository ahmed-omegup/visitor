package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class LiteralFrequencyBuilder implements Function<Expression, Map<String, Integer>> {
    LiteralFrequencyBuilder() {}

    public Map<String, Integer> apply(Expression expression) {
        var frequencies = new LinkedHashMap<String, Integer>();
        var handler = new RecursiveExpression(new LiteralFrequencyBuilderVisitor(frequencies));
        handler.accept(expression);
        return frequencies;
    }
}

final class LiteralFrequencyBuilderVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final Map<String, Integer> frequencies;

    LiteralFrequencyBuilderVisitor(Map<String, Integer> frequencies) {
        this.frequencies = frequencies;
    }
    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(Literal expression) { frequencies.merge(expression.value, 1, Integer::sum); return null; }
}