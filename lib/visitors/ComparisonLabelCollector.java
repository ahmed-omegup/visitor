package lib.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public class ComparisonLabelCollector implements Function<Expression, List<String>> {
    ComparisonLabelCollector() {}

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        expression.accept(new RecursiveExpressionVisitor(new ComparisonLabelCollectorVisitor(labels)));
        return labels;
    }
}

final class ComparisonLabelCollectorVisitor implements Visitor1<Void> {
    private final List<String> labels;

    ComparisonLabelCollectorVisitor(List<String> labels) {
        this.labels = labels;
    }

    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { return null; }
    public Void visit(Subtraction expression) { return null; }
    public Void visit(Multiplication expression) { return null; }
    public Void visit(Division expression) { return null; }
    public Void visit(Negation expression) { return null; }
    public Void visit(Modulo expression) { return null; }
    public Void visit(Exponentiation expression) { return null; }
    public Void visit(Equality expression) { labels.add("Equality"); return null; }
    public Void visit(Inequality expression) { labels.add("Inequality"); return null; }
    public Void visit(LessThan expression) { labels.add("LessThan"); return null; }
    public Void visit(GreaterThan expression) { labels.add("GreaterThan"); return null; }
    public Void visit(LessThanOrEqual expression) { labels.add("LessThanOrEqual"); return null; }
    public Void visit(GreaterThanOrEqual expression) { labels.add("GreaterThanOrEqual"); return null; }
    public Void visit(Conjunction expression) { return null; }
    public Void visit(Disjunction expression) { return null; }
    public Void visit(LogicalNot expression) { return null; }
    public Void visit(Conditional expression) { return null; }
    public Void visit(FunctionCall expression) { return null; }
}