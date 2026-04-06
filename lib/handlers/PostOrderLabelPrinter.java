package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class PostOrderLabelPrinter extends AbstractExpressionFunction<String> {
    PostOrderLabelPrinter() {}
    private List<String> labels;

    public String apply(Expression expression) {
        var labels = new ArrayList<String>();
        collect(expression, labels);
        return String.join(" -> ", labels);
    }
    private void collect(Expression expression, List<String> labels) {
        List<String> previousLabels = this.labels;
        this.labels = labels;
        visitExpression(expression);
        this.labels = previousLabels;
    }

    private void label(String value) {
        labels.add(value);
    }

    public String visit(Literal expression) { label("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { label("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { collect(expression.left, labels); collect(expression.right, labels); label("Addition"); return null; }
    public String visit(Subtraction expression) { collect(expression.left, labels); collect(expression.right, labels); label("Subtraction"); return null; }
    public String visit(Multiplication expression) { collect(expression.left, labels); collect(expression.right, labels); label("Multiplication"); return null; }
    public String visit(Division expression) { collect(expression.dividend, labels); collect(expression.divisor, labels); label("Division"); return null; }
    public String visit(Negation expression) { collect(expression.operand, labels); label("Negation"); return null; }
    public String visit(Modulo expression) { collect(expression.left, labels); collect(expression.right, labels); label("Modulo"); return null; }
    public String visit(Exponentiation expression) { collect(expression.base, labels); collect(expression.exponent, labels); label("Exponentiation"); return null; }
    public String visit(Equality expression) { collect(expression.left, labels); collect(expression.right, labels); label("Equality"); return null; }
    public String visit(Inequality expression) { collect(expression.left, labels); collect(expression.right, labels); label("Inequality"); return null; }
    public String visit(LessThan expression) { collect(expression.left, labels); collect(expression.right, labels); label("LessThan"); return null; }
    public String visit(GreaterThan expression) { collect(expression.left, labels); collect(expression.right, labels); label("GreaterThan"); return null; }
    public String visit(LessThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); label("LessThanOrEqual"); return null; }
    public String visit(GreaterThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); label("GreaterThanOrEqual"); return null; }
    public String visit(Conjunction expression) { collect(expression.left, labels); collect(expression.right, labels); label("Conjunction"); return null; }
    public String visit(Disjunction expression) { collect(expression.left, labels); collect(expression.right, labels); label("Disjunction"); return null; }
    public String visit(LogicalNot expression) { collect(expression.operand, labels); label("LogicalNot"); return null; }
    public String visit(Conditional expression) { collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); label("Conditional"); return null; }
    public String visit(FunctionCall expression) { collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        label("FunctionCall");
        return null;
    }

}