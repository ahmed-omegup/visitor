package lib.handlers;

import lib.expression.*;

abstract class AbstractHandlerVisitor<R> implements Visitor<R> {
    public R visit(Literal expression) { return handle(expression); }
    public R visit(VariableReference expression) { return handle(expression); }
    public R visit(Addition expression) { return handle(expression); }
    public R visit(Subtraction expression) { return handle(expression); }
    public R visit(Multiplication expression) { return handle(expression); }
    public R visit(Division expression) { return handle(expression); }
    public R visit(Negation expression) { return handle(expression); }
    public R visit(Modulo expression) { return handle(expression); }
    public R visit(Exponentiation expression) { return handle(expression); }
    public R visit(Equality expression) { return handle(expression); }
    public R visit(Inequality expression) { return handle(expression); }
    public R visit(LessThan expression) { return handle(expression); }
    public R visit(GreaterThan expression) { return handle(expression); }
    public R visit(LessThanOrEqual expression) { return handle(expression); }
    public R visit(GreaterThanOrEqual expression) { return handle(expression); }
    public R visit(Conjunction expression) { return handle(expression); }
    public R visit(Disjunction expression) { return handle(expression); }
    public R visit(LogicalNot expression) { return handle(expression); }
    public R visit(Conditional expression) { return handle(expression); }
    public R visit(FunctionCall expression) { return handle(expression); }

    protected abstract R handle(Expression expression);
}