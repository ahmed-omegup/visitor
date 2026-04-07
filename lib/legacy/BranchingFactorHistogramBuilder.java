package lib.legacy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class BranchingFactorHistogramBuilder implements Function<Expression, Map<Integer, Integer>> {
    BranchingFactorHistogramBuilder() {}

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        var handler = new RecursiveExpression(new BranchingFactorHistogramBuilderVisitor(histogram));
        handler.accept(expression);
        return histogram;
    }
}

final class BranchingFactorHistogramBuilderVisitor implements ExpressionVisitor<Void>, Consumer<Expression> {
    private final Map<Integer, Integer> histogram;

    BranchingFactorHistogramBuilderVisitor(Map<Integer, Integer> histogram) {
        this.histogram = histogram;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(Literal expression) { return add(0); }
    public Void visit(VariableReference expression) { return add(0); }
    public Void visit(Addition expression) { return add(2); }
    public Void visit(Subtraction expression) { return add(2); }
    public Void visit(Multiplication expression) { return add(2); }
    public Void visit(Division expression) { return add(2); }
    public Void visit(Negation expression) { return add(1); }
    public Void visit(Modulo expression) { return add(2); }
    public Void visit(Exponentiation expression) { return add(2); }
    public Void visit(Equality expression) { return add(2); }
    public Void visit(Inequality expression) { return add(2); }
    public Void visit(LessThan expression) { return add(2); }
    public Void visit(GreaterThan expression) { return add(2); }
    public Void visit(LessThanOrEqual expression) { return add(2); }
    public Void visit(GreaterThanOrEqual expression) { return add(2); }
    public Void visit(Conjunction expression) { return add(2); }
    public Void visit(Disjunction expression) { return add(2); }
    public Void visit(LogicalNot expression) { return add(1); }
    public Void visit(Conditional expression) { return add(3); }
    public Void visit(FunctionCall expression) { return add(expression.arguments.size() + 1); }

    private Void add(int branchingFactor) {
        histogram.put(branchingFactor, histogram.getOrDefault(branchingFactor, 0) + 1);
        return null;
    }
}