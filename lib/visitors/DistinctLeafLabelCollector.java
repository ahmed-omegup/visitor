package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class DistinctLeafLabelCollector extends AbstractExpressionFunction<Set<String>> {
    DistinctLeafLabelCollector() {}
    private Set<String> labels;

    public Set<String> apply(Expression expression) {
        var labels = new LinkedHashSet<String>();
        collect(expression, labels);
        return labels;
    }
    private void collect(Expression expression, Set<String> labels) {
        Set<String> previousLabels = this.labels;
        this.labels = labels;
        visitExpression(expression);
        this.labels = previousLabels;
    }

    public Set<String> visit(Literal expression) { labels.add("literal:" + expression.value); return null; }
    public Set<String> visit(VariableReference expression) { labels.add("variable:" + expression.name); return null; }
    public Set<String> visit(Addition expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Subtraction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Multiplication expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Division expression) { collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
    public Set<String> visit(Negation expression) { collect(expression.operand, labels); return null; }
    public Set<String> visit(Modulo expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Exponentiation expression) { collect(expression.base, labels); collect(expression.exponent, labels); return null; }
    public Set<String> visit(Equality expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Inequality expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(LessThan expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(GreaterThan expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(LessThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(GreaterThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Conjunction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Disjunction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(LogicalNot expression) { collect(expression.operand, labels); return null; }
    public Set<String> visit(Conditional expression) { collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }
    public Set<String> visit(FunctionCall expression) { collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        return null;
    }

}