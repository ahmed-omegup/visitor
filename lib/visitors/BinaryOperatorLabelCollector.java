package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class BinaryOperatorLabelCollector extends AbstractExpressionFunction<List<String>> {
    BinaryOperatorLabelCollector() {}
    private List<String> labels;

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        collect(expression, labels);
        return labels;
    }
    private void collect(Expression expression, List<String> labels) {
        List<String> previousLabels = this.labels;
        this.labels = labels;
        visitExpression(expression);
        this.labels = previousLabels;
    }

    public List<String> visit(Literal expression) { return null; }
    public List<String> visit(VariableReference expression) { return null; }
    public List<String> visit(Addition expression) { labels.add("Addition"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Subtraction expression) { labels.add("Subtraction"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Multiplication expression) { labels.add("Multiplication"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Division expression) { labels.add("Division"); collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
    public List<String> visit(Negation expression) { collect(expression.operand, labels); return null; }
    public List<String> visit(Modulo expression) { labels.add("Modulo"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Exponentiation expression) { labels.add("Exponentiation"); collect(expression.base, labels); collect(expression.exponent, labels); return null; }
    public List<String> visit(Equality expression) { labels.add("Equality"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Inequality expression) { labels.add("Inequality"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThan expression) { labels.add("LessThan"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThan expression) { labels.add("GreaterThan"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThanOrEqual expression) { labels.add("LessThanOrEqual"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { labels.add("GreaterThanOrEqual"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Conjunction expression) { labels.add("Conjunction"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Disjunction expression) { labels.add("Disjunction"); collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LogicalNot expression) { collect(expression.operand, labels); return null; }
    public List<String> visit(Conditional expression) { collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }
    public List<String> visit(FunctionCall expression) { collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        return null;
    }

}