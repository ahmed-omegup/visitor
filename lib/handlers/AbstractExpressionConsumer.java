package lib.handlers;

import java.util.function.Consumer;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.Expression;
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
import lib.expression.ExpressionVisitor;

abstract class AbstractExpressionConsumer implements Consumer<Expression>, ExpressionVisitor<Void> {
    @Override
    public void accept(Expression expression) {
        visitExpression(expression);
    }

    protected final void visitExpression(Expression expression) {
        expression.accept(this);
    }

}