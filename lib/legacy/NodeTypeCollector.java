package lib.legacy;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class NodeTypeCollector implements Function<Expression, Set<String>> {
    NodeTypeCollector() {}

    public Set<String> apply(Expression expression) {
        var types = new LinkedHashSet<String>();
        var handler = new RecursiveExpression(new NodeTypeCollectorVisitor(types));
        handler.accept(expression);
        return types;
    }
}

final class NodeTypeCollectorVisitor implements ExpressionVisitor<Void>, Consumer<Expression> {
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

    public void accept(Expression expression) {
        expression.accept(this);
    }
}