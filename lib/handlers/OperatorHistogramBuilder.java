package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class OperatorHistogramBuilder implements Function<Expression, Map<String, Integer>> {
    OperatorHistogramBuilder() {}

    public Map<String, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<String, Integer>();
        var handler = new RecursiveExpression(new OperatorHistogramBuilderVisitor(histogram));
        handler.accept(expression);
        return histogram;
    }
}

final class OperatorHistogramBuilderVisitor implements Visitor1<Void>, Consumer<Expression> {
    private final Map<String, Integer> histogram;

    OperatorHistogramBuilderVisitor(Map<String, Integer> histogram) {
        this.histogram = histogram;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    private Void hit(String type) {
        histogram.merge(type, 1, Integer::sum);
        return null;
    }

    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { return hit("Addition"); }
    public Void visit(Subtraction expression) { return hit("Subtraction"); }
    public Void visit(Multiplication expression) { return hit("Multiplication"); }
    public Void visit(Division expression) { return hit("Division"); }
    public Void visit(Negation expression) { return hit("Negation"); }
    public Void visit(Modulo expression) { return hit("Modulo"); }
    public Void visit(Exponentiation expression) { return hit("Exponentiation"); }
    public Void visit(Equality expression) { return hit("Equality"); }
    public Void visit(Inequality expression) { return hit("Inequality"); }
    public Void visit(LessThan expression) { return hit("LessThan"); }
    public Void visit(GreaterThan expression) { return hit("GreaterThan"); }
    public Void visit(LessThanOrEqual expression) { return hit("LessThanOrEqual"); }
    public Void visit(GreaterThanOrEqual expression) { return hit("GreaterThanOrEqual"); }
    public Void visit(Conjunction expression) { return hit("Conjunction"); }
    public Void visit(Disjunction expression) { return hit("Disjunction"); }
    public Void visit(LogicalNot expression) { return hit("LogicalNot"); }
    public Void visit(Conditional expression) { return hit("Conditional"); }
    public Void visit(FunctionCall expression) { return hit("FunctionCall"); }
}