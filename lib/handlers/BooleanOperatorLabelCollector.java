package lib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class BooleanOperatorLabelCollector implements Function<Expression, List<String>> {
    BooleanOperatorLabelCollector() {}

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        var handler = new RecursiveExpression(new BooleanOperatorLabelCollectorVisitor(labels));
        handler.accept(expression);
        return labels;
    }
}

final class BooleanOperatorLabelCollectorVisitor implements Visitor1<Void>, Consumer<Expression> {
    private final List<String> labels;

    BooleanOperatorLabelCollectorVisitor(List<String> labels) {
        this.labels = labels;
    }

    public void accept(Expression expression) {
        expression.accept(this);
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
    public Void visit(Equality expression) { return null; }
    public Void visit(Inequality expression) { return null; }
    public Void visit(LessThan expression) { return null; }
    public Void visit(GreaterThan expression) { return null; }
    public Void visit(LessThanOrEqual expression) { return null; }
    public Void visit(GreaterThanOrEqual expression) { return null; }
    public Void visit(Conjunction expression) { labels.add("Conjunction"); return null; }
    public Void visit(Disjunction expression) { labels.add("Disjunction"); return null; }
    public Void visit(LogicalNot expression) { labels.add("LogicalNot"); return null; }
    public Void visit(Conditional expression) { return null; }
    public Void visit(FunctionCall expression) { return null; }
}