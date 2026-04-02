package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class FunctionAritySequenceBuilder {
    public List<Integer> handle(Expression expression) {
        var arities = new ArrayList<Integer>();
        collect(expression, arities);
        return arities;
    }

    private void collect(Expression expression, List<Integer> arities) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(Division expression) { collect(expression.dividend, arities); collect(expression.divisor, arities); return null; }
            public Void visit(Negation expression) { collect(expression.operand, arities); return null; }
            public Void visit(Modulo expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, arities); collect(expression.exponent, arities); return null; }
            public Void visit(Equality expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(Inequality expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(LessThan expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, arities); collect(expression.right, arities); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, arities); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, arities); collect(expression.whenTrue, arities); collect(expression.whenFalse, arities); return null; }
            public Void visit(FunctionCall expression) {
                arities.add(expression.arguments.length);
                collect(expression.callee, arities);
                for (var argument : expression.arguments) {
                    collect(argument, arities);
                }
                return null;
            }
        });
    }
}