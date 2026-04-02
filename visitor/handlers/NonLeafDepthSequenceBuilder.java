package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class NonLeafDepthSequenceBuilder {
    public List<String> handle(Expression expression) {
        var sequence = new ArrayList<String>();
        collect(expression, 0, sequence);
        return sequence;
    }

    private void collect(Expression expression, int depth, List<String> sequence) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { sequence.add(depth + ":Addition"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(Subtraction expression) { sequence.add(depth + ":Subtraction"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(Multiplication expression) { sequence.add(depth + ":Multiplication"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(Division expression) { sequence.add(depth + ":Division"); collect(expression.dividend, depth + 1, sequence); collect(expression.divisor, depth + 1, sequence); return null; }
            public Void visit(Negation expression) { sequence.add(depth + ":Negation"); collect(expression.operand, depth + 1, sequence); return null; }
            public Void visit(Modulo expression) { sequence.add(depth + ":Modulo"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(Exponentiation expression) { sequence.add(depth + ":Exponentiation"); collect(expression.base, depth + 1, sequence); collect(expression.exponent, depth + 1, sequence); return null; }
            public Void visit(Equality expression) { sequence.add(depth + ":Equality"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(Inequality expression) { sequence.add(depth + ":Inequality"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(LessThan expression) { sequence.add(depth + ":LessThan"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(GreaterThan expression) { sequence.add(depth + ":GreaterThan"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(LessThanOrEqual expression) { sequence.add(depth + ":LessThanOrEqual"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(GreaterThanOrEqual expression) { sequence.add(depth + ":GreaterThanOrEqual"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(Conjunction expression) { sequence.add(depth + ":Conjunction"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(Disjunction expression) { sequence.add(depth + ":Disjunction"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
            public Void visit(LogicalNot expression) { sequence.add(depth + ":LogicalNot"); collect(expression.operand, depth + 1, sequence); return null; }
            public Void visit(Conditional expression) { sequence.add(depth + ":Conditional"); collect(expression.condition, depth + 1, sequence); collect(expression.whenTrue, depth + 1, sequence); collect(expression.whenFalse, depth + 1, sequence); return null; }
            public Void visit(FunctionCall expression) {
                sequence.add(depth + ":FunctionCall");
                collect(expression.callee, depth + 1, sequence);
                for (var argument : expression.arguments) {
                    collect(argument, depth + 1, sequence);
                }
                return null;
            }
        });
    }
}