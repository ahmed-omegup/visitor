package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class OperatorHistogramBuilder extends AbstractExpressionFunction<Map<String, Integer>> {
    OperatorHistogramBuilder() {}
    private Map<String, Integer> histogram;

    public Map<String, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<String, Integer>();
        collect(expression, histogram);
        return histogram;
    }
    private void collect(Expression expression, Map<String, Integer> histogram) {
        Map<String, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        visitExpression(expression);
        this.histogram = previousHistogram;
    }

    private void hit(String type) {
        histogram.merge(type, 1, Integer::sum);
    }

    public Map<String, Integer> visit(Literal expression) { return null; }
    public Map<String, Integer> visit(VariableReference expression) { return null; }
    public Map<String, Integer> visit(Addition expression) { hit("Addition"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Subtraction expression) { hit("Subtraction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Multiplication expression) { hit("Multiplication"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Division expression) { hit("Division"); collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
    public Map<String, Integer> visit(Negation expression) { hit("Negation"); collect(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Modulo expression) { hit("Modulo"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Exponentiation expression) { hit("Exponentiation"); collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
    public Map<String, Integer> visit(Equality expression) { hit("Equality"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Inequality expression) { hit("Inequality"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThan expression) { hit("LessThan"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThan expression) { hit("GreaterThan"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) { hit("LessThanOrEqual"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) { hit("GreaterThanOrEqual"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Conjunction expression) { hit("Conjunction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Disjunction expression) { hit("Disjunction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LogicalNot expression) { hit("LogicalNot"); collect(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Conditional expression) { hit("Conditional"); collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
    public Map<String, Integer> visit(FunctionCall expression) { hit("FunctionCall");
        collect(expression.callee, histogram);
        for (var argument : expression.arguments) {
            collect(argument, histogram);
        }
        return null;
    }

}