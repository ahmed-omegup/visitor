package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class ConditionalDepthSequenceBuilder {
    public List<Integer> handle(Expression expression) {
        var depths = new ArrayList<Integer>();
        collect(expression, 0, depths);
        return depths;
    }

    private void collect(Expression expression, int depth, List<Integer> depths) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Division expression) { collect(expression.dividend, depth + 1, depths); collect(expression.divisor, depth + 1, depths); return null; }
            public Void visit(Negation expression) { collect(expression.operand, depth + 1, depths); return null; }
            public Void visit(Modulo expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, depth + 1, depths); collect(expression.exponent, depth + 1, depths); return null; }
            public Void visit(Equality expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Inequality expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(LessThan expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, depth + 1, depths); return null; }
            public Void visit(Conditional expression) {
                depths.add(depth);
                collect(expression.condition, depth + 1, depths);
                collect(expression.whenTrue, depth + 1, depths);
                collect(expression.whenFalse, depth + 1, depths);
                return null;
            }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, depth + 1, depths);
                for (var argument : expression.arguments) {
                    collect(argument, depth + 1, depths);
                }
                return null;
            }
        });
    }
}