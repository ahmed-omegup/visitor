package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

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

public class NodeTypeCollector implements Function<Expression, Set<String>> {
    NodeTypeCollector() {}

    public Set<String> apply(Expression expression) {
        var types = new LinkedHashSet<String>();
        expression.accept(new RecursiveExpressionVisitor(new NodeTypeCollectorVisitor(types)));
        return types;
    }
}

final class NodeTypeCollectorVisitor extends ExpressionVisitorAdapter {
    private final Set<String> types;

    NodeTypeCollectorVisitor(Set<String> types) {
        this.types = types;
    }

    public Void visit(Literal expression) { types.add("Literal"); return null; }
    public Void visit(VariableReference expression) { types.add("VariableReference"); return null; }
    public Void visit(Addition expression) { types.add("Addition"); return null; }
    public Void visit(Subtraction expression) { types.add("Subtraction"); return null; }
    public Void visit(Multiplication expression) { types.add("Multiplication"); return null; }
    public Void visit(Division expression) { types.add("Division"); return null; }
    public Void visit(Negation expression) { types.add("Negation"); return null; }
    public Void visit(Modulo expression) { types.add("Modulo"); return null; }
    public Void visit(Exponentiation expression) { types.add("Exponentiation"); return null; }
    public Void visit(Equality expression) { types.add("Equality"); return null; }
    public Void visit(Inequality expression) { types.add("Inequality"); return null; }
    public Void visit(LessThan expression) { types.add("LessThan"); return null; }
    public Void visit(GreaterThan expression) { types.add("GreaterThan"); return null; }
    public Void visit(LessThanOrEqual expression) { types.add("LessThanOrEqual"); return null; }
    public Void visit(GreaterThanOrEqual expression) { types.add("GreaterThanOrEqual"); return null; }
    public Void visit(Conjunction expression) { types.add("Conjunction"); return null; }
    public Void visit(Disjunction expression) { types.add("Disjunction"); return null; }
    public Void visit(LogicalNot expression) { types.add("LogicalNot"); return null; }
    public Void visit(Conditional expression) { types.add("Conditional"); return null; }
    public Void visit(FunctionCall expression) { types.add("FunctionCall"); return null; }
}