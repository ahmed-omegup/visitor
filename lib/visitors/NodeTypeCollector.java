package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class NodeTypeCollector extends AbstractExpressionFunction<Set<String>> {
    NodeTypeCollector() {}
    private Set<String> types;

    public Set<String> apply(Expression expression) {
        var types = new LinkedHashSet<String>();
        collect(expression, types);
        return types;
    }
    private void collect(Expression expression, Set<String> types) {
        Set<String> previousTypes = this.types;
        this.types = types;
        visitExpression(expression);
        this.types = previousTypes;
    }

    public Set<String> visit(Literal expression) { types.add("Literal"); return null; }
    public Set<String> visit(VariableReference expression) { types.add("VariableReference"); return null; }
    public Set<String> visit(Addition expression) { types.add("Addition"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Subtraction expression) { types.add("Subtraction"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Multiplication expression) { types.add("Multiplication"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Division expression) { types.add("Division"); collect(expression.dividend, types); collect(expression.divisor, types); return null; }
    public Set<String> visit(Negation expression) { types.add("Negation"); collect(expression.operand, types); return null; }
    public Set<String> visit(Modulo expression) { types.add("Modulo"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Exponentiation expression) { types.add("Exponentiation"); collect(expression.base, types); collect(expression.exponent, types); return null; }
    public Set<String> visit(Equality expression) { types.add("Equality"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Inequality expression) { types.add("Inequality"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(LessThan expression) { types.add("LessThan"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(GreaterThan expression) { types.add("GreaterThan"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(LessThanOrEqual expression) { types.add("LessThanOrEqual"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(GreaterThanOrEqual expression) { types.add("GreaterThanOrEqual"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Conjunction expression) { types.add("Conjunction"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Disjunction expression) { types.add("Disjunction"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(LogicalNot expression) { types.add("LogicalNot"); collect(expression.operand, types); return null; }
    public Set<String> visit(Conditional expression) { types.add("Conditional"); collect(expression.condition, types); collect(expression.whenTrue, types); collect(expression.whenFalse, types); return null; }
    public Set<String> visit(FunctionCall expression) { types.add("FunctionCall");
        collect(expression.callee, types);
        for (var argument : expression.arguments) {
            collect(argument, types);
        }
        return null;
    }

}