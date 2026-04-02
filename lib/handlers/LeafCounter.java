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

public class LeafCounter {
    public Integer handle(Expression expression) {
        return count(expression);
    }

    private Integer count(Expression expression) {
        return expression.accept(new Visitor<Integer>() {
            public Integer visit(Literal expression) { return 1; }
            public Integer visit(VariableReference expression) { return 1; }
            public Integer visit(Addition expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(Subtraction expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(Multiplication expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(Division expression) { return count(expression.dividend) + count(expression.divisor); }
            public Integer visit(Negation expression) { return count(expression.operand); }
            public Integer visit(Modulo expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(Exponentiation expression) { return count(expression.base) + count(expression.exponent); }
            public Integer visit(Equality expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(Inequality expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(LessThan expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(GreaterThan expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(LessThanOrEqual expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(GreaterThanOrEqual expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(Conjunction expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(Disjunction expression) { return count(expression.left) + count(expression.right); }
            public Integer visit(LogicalNot expression) { return count(expression.operand); }
            public Integer visit(Conditional expression) { return count(expression.condition) + count(expression.whenTrue) + count(expression.whenFalse); }
            public Integer visit(FunctionCall expression) {
                int total = count(expression.callee);
                for (var argument : expression.arguments) {
                    total += count(argument);
                }
                return total;
            }
        });
    }
}