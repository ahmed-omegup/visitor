package lib.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class BinaryOperatorLabelCollector implements Function<Expression, List<String>> {
    BinaryOperatorLabelCollector() {}

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        var handler = new RecursiveExpression(new BinaryOperatorLabelCollectorVisitor(labels));
        handler.accept(expression);
        return labels;
    }
}

final class BinaryOperatorLabelCollectorVisitor implements ExpressionVisitor<Void>, Consumer<Expression> {
    private final List<String> labels;

    BinaryOperatorLabelCollectorVisitor(List<String> labels) {
        this.labels = labels;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { labels.add("Addition"); return null; }
    public Void visit(Subtraction expression) { labels.add("Subtraction"); return null; }
    public Void visit(Multiplication expression) { labels.add("Multiplication"); return null; }
    public Void visit(Division expression) { labels.add("Division"); return null; }
    public Void visit(Negation expression) { return null; }
    public Void visit(Modulo expression) { labels.add("Modulo"); return null; }
    public Void visit(Exponentiation expression) { labels.add("Exponentiation"); return null; }
    public Void visit(Equality expression) { labels.add("Equality"); return null; }
    public Void visit(Inequality expression) { labels.add("Inequality"); return null; }
    public Void visit(LessThan expression) { labels.add("LessThan"); return null; }
    public Void visit(GreaterThan expression) { labels.add("GreaterThan"); return null; }
    public Void visit(LessThanOrEqual expression) { labels.add("LessThanOrEqual"); return null; }
    public Void visit(GreaterThanOrEqual expression) { labels.add("GreaterThanOrEqual"); return null; }
    public Void visit(Conjunction expression) { labels.add("Conjunction"); return null; }
    public Void visit(Disjunction expression) { labels.add("Disjunction"); return null; }
    public Void visit(LogicalNot expression) { return null; }
    public Void visit(Conditional expression) { return null; }
    public Void visit(FunctionCall expression) { return null; }
}