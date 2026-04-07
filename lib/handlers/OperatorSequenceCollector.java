package lib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class OperatorSequenceCollector implements Function<Expression, List<String>> {
    OperatorSequenceCollector() {}

    public List<String> apply(Expression expression) {
        var sequence = new ArrayList<String>();
        var handler = new RecursiveExpression(new OperatorSequenceCollectorVisitor(sequence));
        handler.accept(expression);
        return sequence;
    }

}

final class OperatorSequenceCollectorVisitor implements ExpressionVisitor<Void>, Consumer<Expression> {
    private final List<String> sequence;

    OperatorSequenceCollectorVisitor(List<String> sequence) {
        this.sequence = sequence;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(Literal expression) { return null; }
    public Void visit(VariableReference expression) { return null; }
    public Void visit(Addition expression) { sequence.add("Addition"); return null; }
    public Void visit(Subtraction expression) { sequence.add("Subtraction"); return null; }
    public Void visit(Multiplication expression) { sequence.add("Multiplication"); return null; }
    public Void visit(Division expression) { sequence.add("Division"); return null; }
    public Void visit(Negation expression) { sequence.add("Negation"); return null; }
    public Void visit(Modulo expression) { sequence.add("Modulo"); return null; }
    public Void visit(Exponentiation expression) { sequence.add("Exponentiation"); return null; }
    public Void visit(Equality expression) { sequence.add("Equality"); return null; }
    public Void visit(Inequality expression) { sequence.add("Inequality"); return null; }
    public Void visit(LessThan expression) { sequence.add("LessThan"); return null; }
    public Void visit(GreaterThan expression) { sequence.add("GreaterThan"); return null; }
    public Void visit(LessThanOrEqual expression) { sequence.add("LessThanOrEqual"); return null; }
    public Void visit(GreaterThanOrEqual expression) { sequence.add("GreaterThanOrEqual"); return null; }
    public Void visit(Conjunction expression) { sequence.add("Conjunction"); return null; }
    public Void visit(Disjunction expression) { sequence.add("Disjunction"); return null; }
    public Void visit(LogicalNot expression) { sequence.add("LogicalNot"); return null; }
    public Void visit(Conditional expression) { sequence.add("Conditional"); return null; }
    public Void visit(FunctionCall expression) { sequence.add("FunctionCall"); return null; }
}