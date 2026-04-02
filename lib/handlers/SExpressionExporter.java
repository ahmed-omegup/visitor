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

public class SExpressionExporter {
    public String handle(Expression expression) {
        return export(expression);
    }

    private String export(Expression expression) {
        return expression.accept(new Visitor<String>() {
            public String visit(Literal expression) {
                return expression.value;
            }

            public String visit(VariableReference expression) {
                return expression.name;
            }

            public String visit(Addition expression) {
                return "(Addition " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(Subtraction expression) {
                return "(Subtraction " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(Multiplication expression) {
                return "(Multiplication " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(Division expression) {
                return "(Division " + export(expression.dividend) + " " + export(expression.divisor) + ")";
            }

            public String visit(Negation expression) {
                return "(Negation " + export(expression.operand) + ")";
            }

            public String visit(Modulo expression) {
                return "(Modulo " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(Exponentiation expression) {
                return "(Exponentiation " + export(expression.base) + " " + export(expression.exponent) + ")";
            }

            public String visit(Equality expression) {
                return "(Equality " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(Inequality expression) {
                return "(Inequality " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(LessThan expression) {
                return "(LessThan " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(GreaterThan expression) {
                return "(GreaterThan " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(LessThanOrEqual expression) {
                return "(LessThanOrEqual " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(GreaterThanOrEqual expression) {
                return "(GreaterThanOrEqual " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(Conjunction expression) {
                return "(Conjunction " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(Disjunction expression) {
                return "(Disjunction " + export(expression.left) + " " + export(expression.right) + ")";
            }

            public String visit(LogicalNot expression) {
                return "(LogicalNot " + export(expression.operand) + ")";
            }

            public String visit(Conditional expression) {
                return "(Conditional " + export(expression.condition) + " " + export(expression.whenTrue) + " " + export(expression.whenFalse) + ")";
            }

            public String visit(FunctionCall expression) {
                var builder = new StringBuilder();
                builder.append("(FunctionCall ").append(export(expression.callee));
                for (var argument : expression.arguments) {
                    builder.append(' ').append(export(argument));
                }
                return builder.append(')').toString();
            }
        });
    }
}