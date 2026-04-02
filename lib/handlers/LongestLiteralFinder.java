package lib.handlers;

import lib.expression.*;

public class LongestLiteralFinder {
    public String handle(Expression expression) {
        return find(expression);
    }

    private String find(Expression expression) {
        return expression.accept(new Visitor<String>() {
            private String longer(String left, String right) {
                return left.length() >= right.length() ? left : right;
            }

            public String visit(Literal expression) { return expression.value; }
            public String visit(VariableReference expression) { return ""; }
            public String visit(Addition expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(Subtraction expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(Multiplication expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(Division expression) { return longer(find(expression.dividend), find(expression.divisor)); }
            public String visit(Negation expression) { return find(expression.operand); }
            public String visit(Modulo expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(Exponentiation expression) { return longer(find(expression.base), find(expression.exponent)); }
            public String visit(Equality expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(Inequality expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(LessThan expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(GreaterThan expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(LessThanOrEqual expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(GreaterThanOrEqual expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(Conjunction expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(Disjunction expression) { return longer(find(expression.left), find(expression.right)); }
            public String visit(LogicalNot expression) { return find(expression.operand); }
            public String visit(Conditional expression) { return longer(find(expression.condition), longer(find(expression.whenTrue), find(expression.whenFalse))); }
            public String visit(FunctionCall expression) {
                var best = find(expression.callee);
                for (var argument : expression.arguments) {
                    best = longer(best, find(argument));
                }
                return best;
            }
        });
    }
}