package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class VariableUsageCounter {
    public Map<String, Integer> handle(Expression expression) {
        var counts = new LinkedHashMap<String, Integer>();
        count(expression, counts);
        return counts;
    }

    private void count(Expression expression, Map<String, Integer> counts) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { counts.merge(expression.name, 1, Integer::sum); return null; }
            public Void visit(Addition expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(Subtraction expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(Multiplication expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(Division expression) { count(expression.dividend, counts); count(expression.divisor, counts); return null; }
            public Void visit(Negation expression) { count(expression.operand, counts); return null; }
            public Void visit(Modulo expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(Exponentiation expression) { count(expression.base, counts); count(expression.exponent, counts); return null; }
            public Void visit(Equality expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(Inequality expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(LessThan expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(GreaterThan expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(LessThanOrEqual expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(GreaterThanOrEqual expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(Conjunction expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(Disjunction expression) { count(expression.left, counts); count(expression.right, counts); return null; }
            public Void visit(LogicalNot expression) { count(expression.operand, counts); return null; }
            public Void visit(Conditional expression) { count(expression.condition, counts); count(expression.whenTrue, counts); count(expression.whenFalse, counts); return null; }
            public Void visit(FunctionCall expression) {
                count(expression.callee, counts);
                for (var argument : expression.arguments) {
                    count(argument, counts);
                }
                return null;
            }
        });
    }
}