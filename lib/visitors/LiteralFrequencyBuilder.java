package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import lib.expression.*;

public class LiteralFrequencyBuilder implements Function<Expression, Map<String, Integer>> {
    LiteralFrequencyBuilder() {}

    public Map<String, Integer> apply(Expression expression) {
        var frequencies = new LinkedHashMap<String, Integer>();
        expression.accept(new RecursiveExpressionVisitor(new LiteralFrequencyBuilderVisitor(frequencies)));
        return frequencies;
    }
}

final class LiteralFrequencyBuilderVisitor extends EmptyVisitor {
    private final Map<String, Integer> frequencies;

    LiteralFrequencyBuilderVisitor(Map<String, Integer> frequencies) {
        this.frequencies = frequencies;
    }

    public Void visit(Literal expression) { frequencies.merge(expression.value, 1, Integer::sum); return null; }
}