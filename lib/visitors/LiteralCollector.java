package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class LiteralCollector extends AbstractExpressionFunction<List<String>> {
    LiteralCollector() {}
    private List<String> literals;

    public List<String> apply(Expression expression) {
        var literals = new ArrayList<String>();
        collect(expression, literals);
        return literals;
    }
    private void collect(Expression expression, List<String> literals) {
        List<String> previousLiterals = this.literals;
        this.literals = literals;
        visitExpression(expression);
        this.literals = previousLiterals;
    }

    public List<String> visit(Literal expression) { literals.add(expression.value); return null; }
    public List<String> visit(VariableReference expression) { return null; }
    public List<String> visit(Addition expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Subtraction expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Multiplication expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Division expression) { collect(expression.dividend, literals); collect(expression.divisor, literals); return null; }
    public List<String> visit(Negation expression) { collect(expression.operand, literals); return null; }
    public List<String> visit(Modulo expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Exponentiation expression) { collect(expression.base, literals); collect(expression.exponent, literals); return null; }
    public List<String> visit(Equality expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Inequality expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(LessThan expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(GreaterThan expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(LessThanOrEqual expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(GreaterThanOrEqual expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Conjunction expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Disjunction expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(LogicalNot expression) { collect(expression.operand, literals); return null; }
    public List<String> visit(Conditional expression) { collect(expression.condition, literals); collect(expression.whenTrue, literals); collect(expression.whenFalse, literals); return null; }
    public List<String> visit(FunctionCall expression) { collect(expression.callee, literals);
        for (var argument : expression.arguments) {
            collect(argument, literals);
        }
        return null;
    }

}