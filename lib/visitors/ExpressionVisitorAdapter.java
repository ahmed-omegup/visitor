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

abstract class ExpressionVisitorAdapter implements Visitor1<Void> {
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