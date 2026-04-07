package lib.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class UnaryOperatorLabelCollector implements Function<Expression, List<String>> {
    UnaryOperatorLabelCollector() {}

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        var handler = new RecursiveExpression(new UnaryOperatorLabelCollectorVisitor(labels));
        handler.accept(expression);
        return labels;
    }
}

final class UnaryOperatorLabelCollectorVisitor implements ExpressionVisitor<Void>, Consumer<Expression> {
    private final List<String> labels;

    UnaryOperatorLabelCollectorVisitor(List<String> labels) {
        this.labels = labels;
    }

    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { return null; }
    public Void visit(Subtraction expression) { return null; }
    public Void visit(Multiplication expression) { return null; }
    public Void visit(Division expression) { return null; }
    public Void visit(Negation expression) { labels.add("Negation"); return null; }
    public Void visit(Modulo expression) { return null; }
    public Void visit(Exponentiation expression) { return null; }
    public Void visit(Equality expression) { return null; }
    public Void visit(Inequality expression) { return null; }
    public Void visit(LessThan expression) { return null; }
    public Void visit(GreaterThan expression) { return null; }
    public Void visit(LessThanOrEqual expression) { return null; }
    public Void visit(GreaterThanOrEqual expression) { return null; }
    public Void visit(Conjunction expression) { return null; }
    public Void visit(Disjunction expression) { return null; }
    public Void visit(LogicalNot expression) { labels.add("LogicalNot"); return null; }
    public Void visit(Conditional expression) { return null; }
    public Void visit(FunctionCall expression) { return null; }

    public void accept(Expression expression) {
        expression.accept(this);
    }
}