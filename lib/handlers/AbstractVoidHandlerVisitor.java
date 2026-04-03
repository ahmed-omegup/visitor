package lib.handlers;

import lib.expression.*;

abstract class AbstractVoidHandlerVisitor implements Visitor<Void> {
    public Void visit(Literal expression) { handle(expression); return null; }
    public Void visit(VariableReference expression) { handle(expression); return null; }
    public Void visit(Addition expression) { handle(expression); return null; }
    public Void visit(Subtraction expression) { handle(expression); return null; }
    public Void visit(Multiplication expression) { handle(expression); return null; }
    public Void visit(Division expression) { handle(expression); return null; }
    public Void visit(Negation expression) { handle(expression); return null; }
    public Void visit(Modulo expression) { handle(expression); return null; }
    public Void visit(Exponentiation expression) { handle(expression); return null; }
    public Void visit(Equality expression) { handle(expression); return null; }
    public Void visit(Inequality expression) { handle(expression); return null; }
    public Void visit(LessThan expression) { handle(expression); return null; }
    public Void visit(GreaterThan expression) { handle(expression); return null; }
    public Void visit(LessThanOrEqual expression) { handle(expression); return null; }
    public Void visit(GreaterThanOrEqual expression) { handle(expression); return null; }
    public Void visit(Conjunction expression) { handle(expression); return null; }
    public Void visit(Disjunction expression) { handle(expression); return null; }
    public Void visit(LogicalNot expression) { handle(expression); return null; }
    public Void visit(Conditional expression) { handle(expression); return null; }
    public Void visit(FunctionCall expression) { handle(expression); return null; }

    protected abstract void handle(Expression expression);
}