package lib.handlers;

import lib.expression.*;

public class MaximumBranchingFactorFinder {
    public int handle(Expression expression) {
        return find(expression);
    }

    private int find(Expression expression) {
        return expression.accept(new Visitor<Integer>() {
            public Integer visit(Literal expression) { return 0; }
            public Integer visit(VariableReference expression) { return 0; }
            public Integer visit(Addition expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(Subtraction expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(Multiplication expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(Division expression) { return Math.max(2, Math.max(find(expression.dividend), find(expression.divisor))); }
            public Integer visit(Negation expression) { return Math.max(1, find(expression.operand)); }
            public Integer visit(Modulo expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(Exponentiation expression) { return Math.max(2, Math.max(find(expression.base), find(expression.exponent))); }
            public Integer visit(Equality expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(Inequality expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(LessThan expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(GreaterThan expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(LessThanOrEqual expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(GreaterThanOrEqual expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(Conjunction expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(Disjunction expression) { return Math.max(2, Math.max(find(expression.left), find(expression.right))); }
            public Integer visit(LogicalNot expression) { return Math.max(1, find(expression.operand)); }
            public Integer visit(Conditional expression) { return Math.max(3, Math.max(find(expression.condition), Math.max(find(expression.whenTrue), find(expression.whenFalse)))); }
            public Integer visit(FunctionCall expression) {
                int max = expression.arguments.length + 1;
                max = Math.max(max, find(expression.callee));
                for (var argument : expression.arguments) {
                    max = Math.max(max, find(argument));
                }
                return max;
            }
        });
    }
}