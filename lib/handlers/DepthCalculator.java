package lib.handlers;

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
import lib.expression.Visitor;

public class DepthCalculator {
    public Integer handle(Expression expression) {
        return depth(expression);
    }

    private Integer depth(Expression expression) {
        return expression.accept(new Visitor<Integer>() {
            public Integer visit(Literal expression) { return 1; }
            public Integer visit(VariableReference expression) { return 1; }
            public Integer visit(Addition expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(Subtraction expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(Multiplication expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(Division expression) { return 1 + Math.max(depth(expression.dividend), depth(expression.divisor)); }
            public Integer visit(Negation expression) { return 1 + depth(expression.operand); }
            public Integer visit(Modulo expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(Exponentiation expression) { return 1 + Math.max(depth(expression.base), depth(expression.exponent)); }
            public Integer visit(Equality expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(Inequality expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(LessThan expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(GreaterThan expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(LessThanOrEqual expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(GreaterThanOrEqual expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(Conjunction expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(Disjunction expression) { return 1 + Math.max(depth(expression.left), depth(expression.right)); }
            public Integer visit(LogicalNot expression) { return 1 + depth(expression.operand); }
            public Integer visit(Conditional expression) {
                return 1 + Math.max(depth(expression.condition), Math.max(depth(expression.whenTrue), depth(expression.whenFalse)));
            }
            public Integer visit(FunctionCall expression) {
                int max = depth(expression.callee);
                for (var argument : expression.arguments) {
                    max = Math.max(max, depth(argument));
                }
                return 1 + max;
            }
        });
    }
}