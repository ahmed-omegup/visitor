package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LiteralFrequencyBuilder extends AbstractExpressionFunction<Map<String, Integer>> {
    LiteralFrequencyBuilder() {}
    private Map<String, Integer> frequencies;

    public Map<String, Integer> apply(Expression expression) {
        var frequencies = new LinkedHashMap<String, Integer>();
        collect(expression, frequencies);
        return frequencies;
    }
    private void collect(Expression expression, Map<String, Integer> frequencies) {
        Map<String, Integer> previousFrequencies = this.frequencies;
        this.frequencies = frequencies;
        visitExpression(expression);
        this.frequencies = previousFrequencies;
    }

    public Map<String, Integer> visit(Literal expression) { frequencies.merge(expression.value, 1, Integer::sum); return null; }
    public Map<String, Integer> visit(VariableReference expression) { return null; }
    public Map<String, Integer> visit(Addition expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Subtraction expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Multiplication expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Division expression) { collect(expression.dividend, frequencies); collect(expression.divisor, frequencies); return null; }
    public Map<String, Integer> visit(Negation expression) { collect(expression.operand, frequencies); return null; }
    public Map<String, Integer> visit(Modulo expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Exponentiation expression) { collect(expression.base, frequencies); collect(expression.exponent, frequencies); return null; }
    public Map<String, Integer> visit(Equality expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Inequality expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(LessThan expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(GreaterThan expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Conjunction expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Disjunction expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(LogicalNot expression) { collect(expression.operand, frequencies); return null; }
    public Map<String, Integer> visit(Conditional expression) { collect(expression.condition, frequencies); collect(expression.whenTrue, frequencies); collect(expression.whenFalse, frequencies); return null; }
    public Map<String, Integer> visit(FunctionCall expression) { collect(expression.callee, frequencies);
        for (var argument : expression.arguments) {
            collect(argument, frequencies);
        }
        return null;
    }

}