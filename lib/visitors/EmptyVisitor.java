package lib.visitors;

import lib.expression.*;

abstract class EmptyVisitor implements Visitor1<Void> {
    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { return null; }
    public Void visit(Subtraction expression) { return null; }
    public Void visit(Multiplication expression) { return null; }
    public Void visit(Division expression) { return null; }
    public Void visit(Negation expression) { return null; }
    public Void visit(Modulo expression) { return null; }
    public Void visit(Exponentiation expression) { return null; }
    public Void visit(Equality expression) { return null; }
    public Void visit(Inequality expression) { return null; }
    public Void visit(LessThan expression) { return null; }
    public Void visit(GreaterThan expression) { return null; }
    public Void visit(LessThanOrEqual expression) { return null; }
    public Void visit(GreaterThanOrEqual expression) { return null; }
    public Void visit(Conjunction expression) { return null; }
    public Void visit(Disjunction expression) { return null; }
    public Void visit(LogicalNot expression) { return null; }
    public Void visit(Conditional expression) { return null; }
    public Void visit(FunctionCall expression) { return null; }
}