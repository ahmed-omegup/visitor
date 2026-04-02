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

public class JsonExporter {
    public String handle(Expression expression) {
        return export(expression);
    }

    private String export(Expression expression) {
        return expression.accept(new Visitor<String>() {
            private String escape(String value) {
                return value.replace("\\", "\\\\").replace("\"", "\\\"");
            }

            public String visit(Literal expression) {
                return "{\"type\":\"Literal\",\"value\":\"" + escape(expression.value) + "\"}";
            }

            public String visit(VariableReference expression) {
                return "{\"type\":\"VariableReference\",\"name\":\"" + escape(expression.name) + "\"}";
            }

            public String visit(Addition expression) {
                return binary("Addition", expression.left, expression.right);
            }

            public String visit(Subtraction expression) {
                return binary("Subtraction", expression.left, expression.right);
            }

            public String visit(Multiplication expression) {
                return binary("Multiplication", expression.left, expression.right);
            }

            public String visit(Division expression) {
                return binary("Division", expression.dividend, expression.divisor);
            }

            public String visit(Negation expression) {
                return unary("Negation", expression.operand);
            }

            public String visit(Modulo expression) {
                return binary("Modulo", expression.left, expression.right);
            }

            public String visit(Exponentiation expression) {
                return binary("Exponentiation", expression.base, expression.exponent);
            }

            public String visit(Equality expression) {
                return binary("Equality", expression.left, expression.right);
            }

            public String visit(Inequality expression) {
                return binary("Inequality", expression.left, expression.right);
            }

            public String visit(LessThan expression) {
                return binary("LessThan", expression.left, expression.right);
            }

            public String visit(GreaterThan expression) {
                return binary("GreaterThan", expression.left, expression.right);
            }

            public String visit(LessThanOrEqual expression) {
                return binary("LessThanOrEqual", expression.left, expression.right);
            }

            public String visit(GreaterThanOrEqual expression) {
                return binary("GreaterThanOrEqual", expression.left, expression.right);
            }

            public String visit(Conjunction expression) {
                return binary("Conjunction", expression.left, expression.right);
            }

            public String visit(Disjunction expression) {
                return binary("Disjunction", expression.left, expression.right);
            }

            public String visit(LogicalNot expression) {
                return unary("LogicalNot", expression.operand);
            }

            public String visit(Conditional expression) {
                return "{\"type\":\"Conditional\",\"children\":["
                    + export(expression.condition) + ","
                    + export(expression.whenTrue) + ","
                    + export(expression.whenFalse) + "]}";
            }

            public String visit(FunctionCall expression) {
                var builder = new StringBuilder();
                builder.append("{\"type\":\"FunctionCall\",\"children\":[")
                    .append(export(expression.callee));
                for (var argument : expression.arguments) {
                    builder.append(',').append(export(argument));
                }
                return builder.append("]}").toString();
            }

            private String unary(String type, Expression operand) {
                return "{\"type\":\"" + type + "\",\"children\":[" + export(operand) + "]}";
            }

            private String binary(String type, Expression left, Expression right) {
                return "{\"type\":\"" + type + "\",\"children\":[" + export(left) + "," + export(right) + "]}";
            }
        });
    }
}