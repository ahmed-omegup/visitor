package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class FunctionArgumentRootCollector implements Visitor<List<String>> {
    FunctionArgumentRootCollector() {}

    private boolean active;
    private boolean labeling;
    private List<String> labels;
    private String currentLabel;

    public List<String> handle(Expression expression) {
        var labels = new ArrayList<String>();
        collect(expression, labels);
        return labels;
    }

    private void collect(Expression expression, List<String> labels) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousLabels = this.labels;
        this.labels = labels;
        expression.accept(this);
        this.labels = previousLabels;
        this.active = previousActive;
    }

    private String label(Expression expression) {
        boolean previousLabeling = this.labeling;
        String previousLabel = this.currentLabel;
        this.labeling = true;
        expression.accept(this);
        String result = this.currentLabel;
        this.currentLabel = previousLabel;
        this.labeling = previousLabeling;
        return result;
    }

    private List<String> setLabel(String value) {
        currentLabel = value;
        return null;
    }

    public List<String> visit(Literal expression) { if (labeling) { return setLabel("Literal"); } if (!active) { return handle(expression); } return null; }
    public List<String> visit(VariableReference expression) { if (labeling) { return setLabel("VariableReference"); } if (!active) { return handle(expression); } return null; }
    public List<String> visit(Addition expression) { if (labeling) { return setLabel("Addition"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Subtraction expression) { if (labeling) { return setLabel("Subtraction"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Multiplication expression) { if (labeling) { return setLabel("Multiplication"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Division expression) { if (labeling) { return setLabel("Division"); } if (!active) { return handle(expression); } collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
    public List<String> visit(Negation expression) { if (labeling) { return setLabel("Negation"); } if (!active) { return handle(expression); } collect(expression.operand, labels); return null; }
    public List<String> visit(Modulo expression) { if (labeling) { return setLabel("Modulo"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Exponentiation expression) { if (labeling) { return setLabel("Exponentiation"); } if (!active) { return handle(expression); } collect(expression.base, labels); collect(expression.exponent, labels); return null; }
    public List<String> visit(Equality expression) { if (labeling) { return setLabel("Equality"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Inequality expression) { if (labeling) { return setLabel("Inequality"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThan expression) { if (labeling) { return setLabel("LessThan"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThan expression) { if (labeling) { return setLabel("GreaterThan"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThanOrEqual expression) { if (labeling) { return setLabel("LessThanOrEqual"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { if (labeling) { return setLabel("GreaterThanOrEqual"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Conjunction expression) { if (labeling) { return setLabel("Conjunction"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Disjunction expression) { if (labeling) { return setLabel("Disjunction"); } if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LogicalNot expression) { if (labeling) { return setLabel("LogicalNot"); } if (!active) { return handle(expression); } collect(expression.operand, labels); return null; }
    public List<String> visit(Conditional expression) { if (labeling) { return setLabel("Conditional"); } if (!active) { return handle(expression); } collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }

    public List<String> visit(FunctionCall expression) {
        if (labeling) {
            return setLabel("FunctionCall");
        }
        if (!active) {
            return handle(expression);
        }
        for (var argument : expression.arguments) {
            labels.add(label(argument));
        }
        collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        return null;
    }
}