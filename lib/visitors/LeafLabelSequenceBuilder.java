package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class LeafLabelSequenceBuilder extends AbstractExpressionFunction<List<String>> {
    LeafLabelSequenceBuilder() {}
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

    public List<String> visit(Literal expression) { labels.add("literal:" + expression.value); return null; }
    public List<String> visit(VariableReference expression) { labels.add("variable:" + expression.name); return null; }
    public List<String> visit(Addition expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Subtraction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Multiplication expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Division expression) { collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
    public List<String> visit(Negation expression) { collect(expression.operand, labels); return null; }
    public List<String> visit(Modulo expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Exponentiation expression) { collect(expression.base, labels); collect(expression.exponent, labels); return null; }
    public List<String> visit(Equality expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Inequality expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThan expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThan expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Conjunction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Disjunction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LogicalNot expression) { collect(expression.operand, labels); return null; }
    public List<String> visit(Conditional expression) { collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }
    public List<String> visit(FunctionCall expression) { collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        return null;
    }

}