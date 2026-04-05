package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class FunctionArgumentRootCollector extends AbstractExpressionFunction<List<String>> {
    FunctionArgumentRootCollector() {}
    private boolean labeling;
    private List<String> labels;
    private String currentLabel;

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

    private String label(Expression expression) {
        boolean previousLabeling = this.labeling;
        String previousLabel = this.currentLabel;
        this.labeling = true;
        visitExpression(expression);
        String result = this.currentLabel;
        this.currentLabel = previousLabel;
        this.labeling = previousLabeling;
        return result;
    }

    private List<String> setLabel(String value) {
        currentLabel = value;
        return null;
    }

    public List<String> visit(Literal expression) { if (labeling) { return setLabel("Literal"); } return null; }
    public List<String> visit(VariableReference expression) { if (labeling) { return setLabel("VariableReference"); } return null; }
    public List<String> visit(Addition expression) { if (labeling) { return setLabel("Addition"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Subtraction expression) { if (labeling) { return setLabel("Subtraction"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Multiplication expression) { if (labeling) { return setLabel("Multiplication"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Division expression) { if (labeling) { return setLabel("Division"); } collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
    public List<String> visit(Negation expression) { if (labeling) { return setLabel("Negation"); } collect(expression.operand, labels); return null; }
    public List<String> visit(Modulo expression) { if (labeling) { return setLabel("Modulo"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Exponentiation expression) { if (labeling) { return setLabel("Exponentiation"); } collect(expression.base, labels); collect(expression.exponent, labels); return null; }
    public List<String> visit(Equality expression) { if (labeling) { return setLabel("Equality"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Inequality expression) { if (labeling) { return setLabel("Inequality"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThan expression) { if (labeling) { return setLabel("LessThan"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThan expression) { if (labeling) { return setLabel("GreaterThan"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThanOrEqual expression) { if (labeling) { return setLabel("LessThanOrEqual"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { if (labeling) { return setLabel("GreaterThanOrEqual"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Conjunction expression) { if (labeling) { return setLabel("Conjunction"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Disjunction expression) { if (labeling) { return setLabel("Disjunction"); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LogicalNot expression) { if (labeling) { return setLabel("LogicalNot"); } collect(expression.operand, labels); return null; }
    public List<String> visit(Conditional expression) { if (labeling) { return setLabel("Conditional"); } collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }

    public List<String> visit(FunctionCall expression) {
        if (labeling) {
            return setLabel("FunctionCall");
        } for (var argument : expression.arguments) {
            labels.add(label(argument));
        }
        collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        return null;
    }
}