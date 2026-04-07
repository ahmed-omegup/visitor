package lib.handlers;

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
import lib.expression.Visitor;

public final class ExpressionClassNameExtractor extends AbstractExpressionFunction<String> {
    ExpressionClassNameExtractor() {}

    public String visit(Literal expression) { return "Literal"; }
    public String visit(VariableReference expression) { return "VariableReference"; }
    public String visit(Addition expression) { return "Addition"; }
    public String visit(Subtraction expression) { return "Subtraction"; }
    public String visit(Multiplication expression) { return "Multiplication"; }
    public String visit(Division expression) { return "Division"; }
    public String visit(Negation expression) { return "Negation"; }
    public String visit(Modulo expression) { return "Modulo"; }
    public String visit(Exponentiation expression) { return "Exponentiation"; }
    public String visit(Equality expression) { return "Equality"; }
    public String visit(Inequality expression) { return "Inequality"; }
    public String visit(LessThan expression) { return "LessThan"; }
    public String visit(GreaterThan expression) { return "GreaterThan"; }
    public String visit(LessThanOrEqual expression) { return "LessThanOrEqual"; }
    public String visit(GreaterThanOrEqual expression) { return "GreaterThanOrEqual"; }
    public String visit(Conjunction expression) { return "Conjunction"; }
    public String visit(Disjunction expression) { return "Disjunction"; }
    public String visit(LogicalNot expression) { return "LogicalNot"; }
    public String visit(Conditional expression) { return "Conditional"; }
    public String visit(FunctionCall expression) { return "FunctionCall"; }
}