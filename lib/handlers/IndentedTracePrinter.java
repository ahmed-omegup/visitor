package lib.handlers;

import lib.expression.*;

public class IndentedTracePrinter extends AbstractVoidHandlerVisitor {
    IndentedTracePrinter() {}

    public void handle(Expression expression) {
        print(expression, 0);
    }

    private void print(Expression expression, int depth) {
        expression.accept(new Visitor<Void>() {
            private void line(String value) {
                System.out.println("  ".repeat(depth) + value);
            }

            public Void visit(Literal expression) {
                line("Literal(" + expression.value + ")");
                return null;
            }

            public Void visit(VariableReference expression) {
                line("VariableReference(" + expression.name + ")");
                return null;
            }

            public Void visit(Addition expression) {
                line("Addition");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(Subtraction expression) {
                line("Subtraction");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(Multiplication expression) {
                line("Multiplication");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(Division expression) {
                line("Division");
                print(expression.dividend, depth + 1);
                print(expression.divisor, depth + 1);
                return null;
            }

            public Void visit(Negation expression) {
                line("Negation");
                print(expression.operand, depth + 1);
                return null;
            }

            public Void visit(Modulo expression) {
                line("Modulo");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(Exponentiation expression) {
                line("Exponentiation");
                print(expression.base, depth + 1);
                print(expression.exponent, depth + 1);
                return null;
            }

            public Void visit(Equality expression) {
                line("Equality");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(Inequality expression) {
                line("Inequality");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(LessThan expression) {
                line("LessThan");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(GreaterThan expression) {
                line("GreaterThan");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(LessThanOrEqual expression) {
                line("LessThanOrEqual");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(GreaterThanOrEqual expression) {
                line("GreaterThanOrEqual");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(Conjunction expression) {
                line("Conjunction");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(Disjunction expression) {
                line("Disjunction");
                print(expression.left, depth + 1);
                print(expression.right, depth + 1);
                return null;
            }

            public Void visit(LogicalNot expression) {
                line("LogicalNot");
                print(expression.operand, depth + 1);
                return null;
            }

            public Void visit(Conditional expression) {
                line("Conditional");
                print(expression.condition, depth + 1);
                print(expression.whenTrue, depth + 1);
                print(expression.whenFalse, depth + 1);
                return null;
            }

            public Void visit(FunctionCall expression) {
                line("FunctionCall");
                print(expression.callee, depth + 1);
                for (var argument : expression.arguments) {
                    print(argument, depth + 1);
                }
                return null;
            }
        });
    }
}