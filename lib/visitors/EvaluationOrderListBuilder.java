package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class EvaluationOrderListBuilder extends AbstractExpressionFunction<List<String>> {
    EvaluationOrderListBuilder() {}
    private List<String> steps;

    public List<String> apply(Expression expression) {
        var steps = new ArrayList<String>();
        append(expression, steps);
        return steps;
    }
    private void append(Expression expression, List<String> steps) {
        List<String> previousSteps = this.steps;
        this.steps = steps;
        visitExpression(expression);
        this.steps = previousSteps;
    }

    private void step(String value) {
        steps.add(value);
    }

    public List<String> visit(Literal expression) { step("Literal(" + expression.value + ")"); return null; }
    public List<String> visit(VariableReference expression) { step("VariableReference(" + expression.name + ")"); return null; }
    public List<String> visit(Addition expression) { step("Addition"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Subtraction expression) { step("Subtraction"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Multiplication expression) { step("Multiplication"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Division expression) { step("Division"); append(expression.dividend, steps); append(expression.divisor, steps); return null; }
    public List<String> visit(Negation expression) { step("Negation"); append(expression.operand, steps); return null; }
    public List<String> visit(Modulo expression) { step("Modulo"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Exponentiation expression) { step("Exponentiation"); append(expression.base, steps); append(expression.exponent, steps); return null; }
    public List<String> visit(Equality expression) { step("Equality"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Inequality expression) { step("Inequality"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(LessThan expression) { step("LessThan"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(GreaterThan expression) { step("GreaterThan"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(LessThanOrEqual expression) { step("LessThanOrEqual"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { step("GreaterThanOrEqual"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Conjunction expression) { step("Conjunction"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(Disjunction expression) { step("Disjunction"); append(expression.left, steps); append(expression.right, steps); return null; }
    public List<String> visit(LogicalNot expression) { step("LogicalNot"); append(expression.operand, steps); return null; }
    public List<String> visit(Conditional expression) { step("Conditional"); append(expression.condition, steps); append(expression.whenTrue, steps); append(expression.whenFalse, steps); return null; }
    public List<String> visit(FunctionCall expression) { step("FunctionCall");
        append(expression.callee, steps);
        for (var argument : expression.arguments) {
            append(argument, steps);
        }
        return null;
    }

}