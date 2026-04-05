package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class OperatorSequenceCollector extends AbstractExpressionFunction<List<String>> {
    OperatorSequenceCollector() {}
    private List<String> sequence;

    public List<String> apply(Expression expression) {
        var sequence = new ArrayList<String>();
        collect(expression, sequence);
        return sequence;
    }
    private void collect(Expression expression, List<String> sequence) {
        List<String> previousSequence = this.sequence;
        this.sequence = sequence;
        visitExpression(expression);
        this.sequence = previousSequence;
    }

    public List<String> visit(Literal expression) { return null; }
    public List<String> visit(VariableReference expression) { return null; }
    public List<String> visit(Addition expression) { sequence.add("Addition"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Subtraction expression) { sequence.add("Subtraction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Multiplication expression) { sequence.add("Multiplication"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Division expression) { sequence.add("Division"); collect(expression.dividend, sequence); collect(expression.divisor, sequence); return null; }
    public List<String> visit(Negation expression) { sequence.add("Negation"); collect(expression.operand, sequence); return null; }
    public List<String> visit(Modulo expression) { sequence.add("Modulo"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Exponentiation expression) { sequence.add("Exponentiation"); collect(expression.base, sequence); collect(expression.exponent, sequence); return null; }
    public List<String> visit(Equality expression) { sequence.add("Equality"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Inequality expression) { sequence.add("Inequality"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(LessThan expression) { sequence.add("LessThan"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(GreaterThan expression) { sequence.add("GreaterThan"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(LessThanOrEqual expression) { sequence.add("LessThanOrEqual"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { sequence.add("GreaterThanOrEqual"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Conjunction expression) { sequence.add("Conjunction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Disjunction expression) { sequence.add("Disjunction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(LogicalNot expression) { sequence.add("LogicalNot"); collect(expression.operand, sequence); return null; }
    public List<String> visit(Conditional expression) { sequence.add("Conditional"); collect(expression.condition, sequence); collect(expression.whenTrue, sequence); collect(expression.whenFalse, sequence); return null; }
    public List<String> visit(FunctionCall expression) { sequence.add("FunctionCall");
        collect(expression.callee, sequence);
        for (var argument : expression.arguments) {
            collect(argument, sequence);
        }
        return null;
    }

}