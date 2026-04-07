package lib.legacy;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import lib.expression.*;

class VariableName extends EmptyVisitor<String> {
    public String visit(VariableReference expression) { return expression.name; }
}

public class FunctionNameCollector implements Function<Expression, Set<String>> {
    FunctionNameCollector() {}

    public Set<String> apply(Expression expression) {
        var names = new LinkedHashSet<String>();
        var visitor = new FunctionNameVisitor(names);
        expression.accept(visitor);
        return names;
    }
}

class FunctionNameVisitor implements ExpressionVisitor<Void> {
    private final VariableName variableName = new VariableName();
    private Set<String> names;

    FunctionNameVisitor(Set<String> names) {
        this.names = names;
    }

    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(Subtraction expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(Multiplication expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(Division expression) { expression.dividend.accept(this); expression.divisor.accept(this); return null; }
    public Void visit(Negation expression) { expression.operand.accept(this); return null; }
    public Void visit(Modulo expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(Exponentiation expression) { expression.base.accept(this); expression.exponent.accept(this); return null; }
    public Void visit(Equality expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(Inequality expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(LessThan expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(GreaterThan expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(LessThanOrEqual expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(GreaterThanOrEqual expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(Conjunction expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(Disjunction expression) { expression.left.accept(this); expression.right.accept(this); return null; }
    public Void visit(LogicalNot expression) { expression.operand.accept(this); return null; }
    public Void visit(Conditional expression) { expression.condition.accept(this); expression.whenTrue.accept(this); expression.whenFalse.accept(this); return null; }

    public Void visit(FunctionCall expression) {
        String calleeName = expression.callee.accept(variableName);
        if (calleeName != null) {
            names.add(calleeName);
        }
        expression.callee.accept(this);
        for (var argument : expression.arguments) {
            argument.accept(this);
        }
        return null;
    }
}