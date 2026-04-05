package lib.visitors;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Expression;
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

public final class ExpressionClassNameExtractor implements Visitor<String> {
    ExpressionClassNameExtractor() {}

    private String name(Expression expression) {
        return expression.getClass().getSimpleName();
    }

    public String visit(Literal expression) { return name(expression); }
    public String visit(VariableReference expression) { return name(expression); }
    public String visit(Addition expression) { return name(expression); }
    public String visit(Subtraction expression) { return name(expression); }
    public String visit(Multiplication expression) { return name(expression); }
    public String visit(Division expression) { return name(expression); }
    public String visit(Negation expression) { return name(expression); }
    public String visit(Modulo expression) { return name(expression); }
    public String visit(Exponentiation expression) { return name(expression); }
    public String visit(Equality expression) { return name(expression); }
    public String visit(Inequality expression) { return name(expression); }
    public String visit(LessThan expression) { return name(expression); }
    public String visit(GreaterThan expression) { return name(expression); }
    public String visit(LessThanOrEqual expression) { return name(expression); }
    public String visit(GreaterThanOrEqual expression) { return name(expression); }
    public String visit(Conjunction expression) { return name(expression); }
    public String visit(Disjunction expression) { return name(expression); }
    public String visit(LogicalNot expression) { return name(expression); }
    public String visit(Conditional expression) { return name(expression); }
    public String visit(FunctionCall expression) { return name(expression); }
}