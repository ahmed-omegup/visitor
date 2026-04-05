package lib.visitors;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.FunctionCall;
import lib.expression.GreaterThan;
import lib.expression.GreaterThanOrEqual;
import lib.expression.Inequality;
import lib.expression.LessThan;
import lib.expression.LessThanOrEqual;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Modulo;
import lib.expression.Multiplication;
import lib.expression.Negation;
import lib.expression.Subtraction;
import lib.expression.VariableReference;
import lib.expression.Visitor1;

final class RecursiveExpressionVisitor implements Visitor1<Void> {
    private final Visitor1<Void> visitor;

    RecursiveExpressionVisitor(Visitor1<Void> visitor) {
        this.visitor = visitor;
    }

    public Void visit(Literal expression) {
        return visitor.visit(expression);
    }

    public Void visit(VariableReference expression) {
        return visitor.visit(expression);
    }

    public Void visit(Addition expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(Subtraction expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(Multiplication expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(Division expression) {
        visitor.visit(expression);
        expression.dividend.accept(this);
        expression.divisor.accept(this);
        return null;
    }

    public Void visit(Negation expression) {
        visitor.visit(expression);
        expression.operand.accept(this);
        return null;
    }

    public Void visit(Modulo expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(Exponentiation expression) {
        visitor.visit(expression);
        expression.base.accept(this);
        expression.exponent.accept(this);
        return null;
    }

    public Void visit(Equality expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(Inequality expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(LessThan expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(GreaterThan expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(LessThanOrEqual expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(GreaterThanOrEqual expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(Conjunction expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(Disjunction expression) {
        visitor.visit(expression);
        expression.left.accept(this);
        expression.right.accept(this);
        return null;
    }

    public Void visit(LogicalNot expression) {
        visitor.visit(expression);
        expression.operand.accept(this);
        return null;
    }

    public Void visit(Conditional expression) {
        visitor.visit(expression);
        expression.condition.accept(this);
        expression.whenTrue.accept(this);
        expression.whenFalse.accept(this);
        return null;
    }

    public Void visit(FunctionCall expression) {
        visitor.visit(expression);
        expression.callee.accept(this);
        for (var argument : expression.arguments) {
            argument.accept(this);
        }
        return null;
    }
}