package lib.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public class ArithmeticOperatorLabelCollector implements Function<Expression, List<String>> {
    ArithmeticOperatorLabelCollector() {}

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        expression.accept(new RecursiveExpressionVisitor(new ArithmeticOperatorLabelCollectorVisitor(labels)));
        return labels;
    }
}

final class ArithmeticOperatorLabelCollectorVisitor implements Visitor1<Void> {
    private final List<String> labels;

    ArithmeticOperatorLabelCollectorVisitor(List<String> labels) {
        this.labels = labels;
    }

    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { labels.add("Addition"); return null; }
    public Void visit(Subtraction expression) { labels.add("Subtraction"); return null; }
    public Void visit(Multiplication expression) { labels.add("Multiplication"); return null; }
    public Void visit(Division expression) { labels.add("Division"); return null; }
    public Void visit(Negation expression) { labels.add("Negation"); return null; }
    public Void visit(Modulo expression) { labels.add("Modulo"); return null; }
    public Void visit(Exponentiation expression) { labels.add("Exponentiation"); return null; }
    public Void visit(Equality expression) { return null; }
    public Void visit(Inequality expression) { return null; }
    public Void visit(LessThan expression) { return null; }
    public Void visit(GreaterThan expression) { return null; }
    public Void visit(LessThanOrEqual expression) { return null; }
    public Void visit(GreaterThanOrEqual expression) { return null; }
    public Void visit(Conjunction expression) { return null; }
    public Void visit(Disjunction expression) { return null; }
    public Void visit(LogicalNot expression) { return null; }
    public Void visit(Conditional expression) { return null; }
    public Void visit(FunctionCall expression) { return null; }
}