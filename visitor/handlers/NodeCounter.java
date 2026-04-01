package visitor.handlers;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Disjunction;
import visitor.expression.Division;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.Expression;
import visitor.expression.FunctionCall;
import visitor.expression.GreaterThan;
import visitor.expression.GreaterThanOrEqual;
import visitor.expression.Inequality;
import visitor.expression.LessThan;
import visitor.expression.LessThanOrEqual;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Modulo;
import visitor.expression.Multiplication;
import visitor.expression.Negation;
import visitor.expression.Subtraction;
import visitor.expression.VariableReference;
import visitor.expression.Visitor;

public class NodeCounter {
    public Integer handle(Expression expression) {
        return count(expression);
    }

    private Integer count(Expression expression) {
        return expression.accept(new Visitor<Integer>() {
            public Integer visit(Literal expression) { return 1; }
            public Integer visit(VariableReference expression) { return 1; }
            public Integer visit(Addition expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(Subtraction expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(Multiplication expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(Division expression) { return 1 + count(expression.dividend) + count(expression.divisor); }
            public Integer visit(Negation expression) { return 1 + count(expression.operand); }
            public Integer visit(Modulo expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(Exponentiation expression) { return 1 + count(expression.base) + count(expression.exponent); }
            public Integer visit(Equality expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(Inequality expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(LessThan expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(GreaterThan expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(LessThanOrEqual expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(GreaterThanOrEqual expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(Conjunction expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(Disjunction expression) { return 1 + count(expression.left) + count(expression.right); }
            public Integer visit(LogicalNot expression) { return 1 + count(expression.operand); }
            public Integer visit(Conditional expression) { return 1 + count(expression.condition) + count(expression.whenTrue) + count(expression.whenFalse); }
            public Integer visit(FunctionCall expression) {
                int total = 1 + count(expression.callee);
                for (var argument : expression.arguments) {
                    total += count(argument);
                }
                return total;
            }
        });
    }
}