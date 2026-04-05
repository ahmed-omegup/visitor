package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import lib.expression.*;

class VariableName implements Visitor1<String> {
    public String visit(Literal expression) { return null; }
    public String visit(VariableReference expression) { return expression.name; }
    public String visit(Addition expression) { return null; }
    public String visit(Subtraction expression) { return null; }
    public String visit(Multiplication expression) { return null; }
    public String visit(Division expression) { return null; }
    public String visit(Negation expression) { return null; }
    public String visit(Modulo expression) { return null; }
    public String visit(Exponentiation expression) { return null; }
    public String visit(Equality expression) { return null; }
    public String visit(Inequality expression) { return null; }
    public String visit(LessThan expression) { return null; }
    public String visit(GreaterThan expression) { return null; }
    public String visit(LessThanOrEqual expression) { return null; }
    public String visit(GreaterThanOrEqual expression) { return null; }
    public String visit(Conjunction expression) { return null; }
    public String visit(Disjunction expression) { return null; }
    public String visit(LogicalNot expression) { return null; }
    public String visit(Conditional expression) { return null; }
    public String visit(FunctionCall expression) { return null; }
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

class FunctionNameVisitor implements Visitor1<Void> {
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
        var calleeName = expression.callee.accept(variableName);
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