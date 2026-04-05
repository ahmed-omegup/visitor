package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class NodeHistogramBuilder extends AbstractExpressionFunction<Map<String, Integer>> {
    NodeHistogramBuilder() {}
    private Map<String, Integer> histogram;

    public Map<String, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<String, Integer>();
        populate(expression, histogram);
        return histogram;
    }
    private void populate(Expression expression, Map<String, Integer> histogram) {
        Map<String, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        visitExpression(expression);
        this.histogram = previousHistogram;
    }

    private void hit(String type) {
        histogram.merge(type, 1, Integer::sum);
    }

    public Map<String, Integer> visit(Literal expression) { hit("Literal"); return null; }
    public Map<String, Integer> visit(VariableReference expression) { hit("VariableReference"); return null; }
    public Map<String, Integer> visit(Addition expression) { hit("Addition"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Subtraction expression) { hit("Subtraction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Multiplication expression) { hit("Multiplication"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Division expression) { hit("Division"); populate(expression.dividend, histogram); populate(expression.divisor, histogram); return null; }
    public Map<String, Integer> visit(Negation expression) { hit("Negation"); populate(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Modulo expression) { hit("Modulo"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Exponentiation expression) { hit("Exponentiation"); populate(expression.base, histogram); populate(expression.exponent, histogram); return null; }
    public Map<String, Integer> visit(Equality expression) { hit("Equality"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Inequality expression) { hit("Inequality"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThan expression) { hit("LessThan"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThan expression) { hit("GreaterThan"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) { hit("LessThanOrEqual"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) { hit("GreaterThanOrEqual"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Conjunction expression) { hit("Conjunction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Disjunction expression) { hit("Disjunction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LogicalNot expression) { hit("LogicalNot"); populate(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Conditional expression) { hit("Conditional"); populate(expression.condition, histogram); populate(expression.whenTrue, histogram); populate(expression.whenFalse, histogram); return null; }
    public Map<String, Integer> visit(FunctionCall expression) { hit("FunctionCall");
        populate(expression.callee, histogram);
        for (var argument : expression.arguments) {
            populate(argument, histogram);
        }
        return null;
    }

}