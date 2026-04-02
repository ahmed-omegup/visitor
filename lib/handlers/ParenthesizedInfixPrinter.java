package lib.handlers;

import lib.expression.*;

public class ParenthesizedInfixPrinter {
    public String handle(Expression expression) {
        return render(expression);
    }

    private String render(Expression expression) {
        return expression.accept(new Visitor<String>() {
            public String visit(Literal expression) {
                return expression.value;
            }

            public String visit(VariableReference expression) {
                return expression.name;
            }

            public String visit(Addition expression) {
                return "(" + render(expression.left) + " + " + render(expression.right) + ")";
            }

            public String visit(Subtraction expression) {
                return "(" + render(expression.left) + " - " + render(expression.right) + ")";
            }

            public String visit(Multiplication expression) {
                return "(" + render(expression.left) + " * " + render(expression.right) + ")";
            }

            public String visit(Division expression) {
                return "(" + render(expression.dividend) + " / " + render(expression.divisor) + ")";
            }

            public String visit(Negation expression) {
                return "(-" + render(expression.operand) + ")";
            }

            public String visit(Modulo expression) {
                return "(" + render(expression.left) + " % " + render(expression.right) + ")";
            }

            public String visit(Exponentiation expression) {
                return "(" + render(expression.base) + " ^ " + render(expression.exponent) + ")";
            }

            public String visit(Equality expression) {
                return "(" + render(expression.left) + " == " + render(expression.right) + ")";
            }

            public String visit(Inequality expression) {
                return "(" + render(expression.left) + " != " + render(expression.right) + ")";
            }

            public String visit(LessThan expression) {
                return "(" + render(expression.left) + " < " + render(expression.right) + ")";
            }

            public String visit(GreaterThan expression) {
                return "(" + render(expression.left) + " > " + render(expression.right) + ")";
            }

            public String visit(LessThanOrEqual expression) {
                return "(" + render(expression.left) + " <= " + render(expression.right) + ")";
            }

            public String visit(GreaterThanOrEqual expression) {
                return "(" + render(expression.left) + " >= " + render(expression.right) + ")";
            }

            public String visit(Conjunction expression) {
                return "(" + render(expression.left) + " && " + render(expression.right) + ")";
            }

            public String visit(Disjunction expression) {
                return "(" + render(expression.left) + " || " + render(expression.right) + ")";
            }

            public String visit(LogicalNot expression) {
                return "(!" + render(expression.operand) + ")";
            }

            public String visit(Conditional expression) {
                return "(" + render(expression.condition) + " ? " + render(expression.whenTrue) + " : " + render(expression.whenFalse) + ")";
            }

            public String visit(FunctionCall expression) {
                var builder = new StringBuilder();
                builder.append(render(expression.callee)).append('(');
                for (int index = 0; index < expression.arguments.length; index++) {
                    if (index > 0) {
                        builder.append(", ");
                    }
                    builder.append(render(expression.arguments[index]));
                }
                return builder.append(')').toString();
            }
        });
    }
}