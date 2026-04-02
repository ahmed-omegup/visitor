package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class OperatorSequenceCollector {
    public List<String> handle(Expression expression) {
        var sequence = new ArrayList<String>();
        collect(expression, sequence);
        return sequence;
    }

    private void collect(Expression expression, List<String> sequence) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { sequence.add("Addition"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(Subtraction expression) { sequence.add("Subtraction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(Multiplication expression) { sequence.add("Multiplication"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(Division expression) { sequence.add("Division"); collect(expression.dividend, sequence); collect(expression.divisor, sequence); return null; }
            public Void visit(Negation expression) { sequence.add("Negation"); collect(expression.operand, sequence); return null; }
            public Void visit(Modulo expression) { sequence.add("Modulo"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(Exponentiation expression) { sequence.add("Exponentiation"); collect(expression.base, sequence); collect(expression.exponent, sequence); return null; }
            public Void visit(Equality expression) { sequence.add("Equality"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(Inequality expression) { sequence.add("Inequality"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(LessThan expression) { sequence.add("LessThan"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(GreaterThan expression) { sequence.add("GreaterThan"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(LessThanOrEqual expression) { sequence.add("LessThanOrEqual"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(GreaterThanOrEqual expression) { sequence.add("GreaterThanOrEqual"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(Conjunction expression) { sequence.add("Conjunction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(Disjunction expression) { sequence.add("Disjunction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
            public Void visit(LogicalNot expression) { sequence.add("LogicalNot"); collect(expression.operand, sequence); return null; }
            public Void visit(Conditional expression) { sequence.add("Conditional"); collect(expression.condition, sequence); collect(expression.whenTrue, sequence); collect(expression.whenFalse, sequence); return null; }
            public Void visit(FunctionCall expression) {
                sequence.add("FunctionCall");
                collect(expression.callee, sequence);
                for (var argument : expression.arguments) {
                    collect(argument, sequence);
                }
                return null;
            }
        });
    }
}