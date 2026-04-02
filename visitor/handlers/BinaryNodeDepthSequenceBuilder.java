package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class BinaryNodeDepthSequenceBuilder {
    public List<Integer> handle(Expression expression) {
        var depths = new ArrayList<Integer>();
        collect(expression, 0, depths);
        return depths;
    }

    private void collect(Expression expression, int depth, List<Integer> depths) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Subtraction expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Multiplication expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Division expression) { depths.add(depth); collect(expression.dividend, depth + 1, depths); collect(expression.divisor, depth + 1, depths); return null; }
            public Void visit(Negation expression) { collect(expression.operand, depth + 1, depths); return null; }
            public Void visit(Modulo expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Exponentiation expression) { depths.add(depth); collect(expression.base, depth + 1, depths); collect(expression.exponent, depth + 1, depths); return null; }
            public Void visit(Equality expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Inequality expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(LessThan expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(GreaterThan expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(LessThanOrEqual expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(GreaterThanOrEqual expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Conjunction expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(Disjunction expression) { depths.add(depth); collect(expression.left, depth + 1, depths); collect(expression.right, depth + 1, depths); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, depth + 1, depths); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, depth + 1, depths); collect(expression.whenTrue, depth + 1, depths); collect(expression.whenFalse, depth + 1, depths); return null; }
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