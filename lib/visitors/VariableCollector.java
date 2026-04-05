package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class VariableCollector extends AbstractExpressionFunction<Set<String>> {
    VariableCollector() {}
    private Set<String> names;

    public Set<String> apply(Expression expression) {
        var names = new LinkedHashSet<String>();
        collect(expression, names);
        return names;
    }
    private void collect(Expression expression, Set<String> names) {
        Set<String> previousNames = this.names;
        this.names = names;
        visitExpression(expression);
        this.names = previousNames;
    }

    public Set<String> visit(Literal expression) { return null; }
    public Set<String> visit(VariableReference expression) { names.add(expression.name); return null; }
    public Set<String> visit(Addition expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Subtraction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Multiplication expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Division expression) { collect(expression.dividend, names); collect(expression.divisor, names); return null; }
    public Set<String> visit(Negation expression) { collect(expression.operand, names); return null; }
    public Set<String> visit(Modulo expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Exponentiation expression) { collect(expression.base, names); collect(expression.exponent, names); return null; }
    public Set<String> visit(Equality expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Inequality expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(LessThan expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(GreaterThan expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(LessThanOrEqual expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(GreaterThanOrEqual expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Conjunction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(Disjunction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
    public Set<String> visit(LogicalNot expression) { collect(expression.operand, names); return null; }
    public Set<String> visit(Conditional expression) { collect(expression.condition, names); collect(expression.whenTrue, names); collect(expression.whenFalse, names); return null; }
    public Set<String> visit(FunctionCall expression) { collect(expression.callee, names);
        for (var argument : expression.arguments) {
            collect(argument, names);
        }
        return null;
    }

}