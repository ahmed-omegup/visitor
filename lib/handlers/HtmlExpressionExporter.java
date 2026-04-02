package lib.handlers;

import lib.expression.*;

public class HtmlExpressionExporter {
    public String handle(Expression expression) {
        return export(expression);
    }

    private String export(Expression expression) {
        return expression.accept(new Visitor<String>() {
            private String escape(String value) {
                return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
            }

            private String wrap(String type, String content) {
                return "<span class=\"expression " + type + "\">" + content + "</span>";
            }

            public String visit(Literal expression) { return wrap("literal", escape(expression.value)); }
            public String visit(VariableReference expression) { return wrap("variable-reference", escape(expression.name)); }
            public String visit(Addition expression) { return wrap("addition", export(expression.left) + " <span class=\"operator\">+</span> " + export(expression.right)); }
            public String visit(Subtraction expression) { return wrap("subtraction", export(expression.left) + " <span class=\"operator\">-</span> " + export(expression.right)); }
            public String visit(Multiplication expression) { return wrap("multiplication", export(expression.left) + " <span class=\"operator\">*</span> " + export(expression.right)); }
            public String visit(Division expression) { return wrap("division", export(expression.dividend) + " <span class=\"operator\">/</span> " + export(expression.divisor)); }
            public String visit(Negation expression) { return wrap("negation", "<span class=\"operator\">-</span>" + export(expression.operand)); }
            public String visit(Modulo expression) { return wrap("modulo", export(expression.left) + " <span class=\"operator\">%</span> " + export(expression.right)); }
            public String visit(Exponentiation expression) { return wrap("exponentiation", export(expression.base) + " <span class=\"operator\">^</span> " + export(expression.exponent)); }
            public String visit(Equality expression) { return wrap("equality", export(expression.left) + " <span class=\"operator\">==</span> " + export(expression.right)); }
            public String visit(Inequality expression) { return wrap("inequality", export(expression.left) + " <span class=\"operator\">!=</span> " + export(expression.right)); }
            public String visit(LessThan expression) { return wrap("less-than", export(expression.left) + " <span class=\"operator\">&lt;</span> " + export(expression.right)); }
            public String visit(GreaterThan expression) { return wrap("greater-than", export(expression.left) + " <span class=\"operator\">&gt;</span> " + export(expression.right)); }
            public String visit(LessThanOrEqual expression) { return wrap("less-than-or-equal", export(expression.left) + " <span class=\"operator\">&lt;=</span> " + export(expression.right)); }
            public String visit(GreaterThanOrEqual expression) { return wrap("greater-than-or-equal", export(expression.left) + " <span class=\"operator\">&gt;=</span> " + export(expression.right)); }
            public String visit(Conjunction expression) { return wrap("conjunction", export(expression.left) + " <span class=\"operator\">&amp;&amp;</span> " + export(expression.right)); }
            public String visit(Disjunction expression) { return wrap("disjunction", export(expression.left) + " <span class=\"operator\">||</span> " + export(expression.right)); }
            public String visit(LogicalNot expression) { return wrap("logical-not", "<span class=\"operator\">!</span>" + export(expression.operand)); }
            public String visit(Conditional expression) {
                return wrap("conditional", export(expression.condition) + " <span class=\"operator\">?</span> " + export(expression.whenTrue)
                    + " <span class=\"operator\">:</span> " + export(expression.whenFalse));
            }
            public String visit(FunctionCall expression) {
                var builder = new StringBuilder();
                builder.append(export(expression.callee)).append("<span class=\"punctuation\">(</span>");
                for (int index = 0; index < expression.arguments.length; index++) {
                    if (index > 0) {
                        builder.append("<span class=\"punctuation\">, </span>");
                    }
                    builder.append(export(expression.arguments[index]));
                }
                return wrap("function-call", builder.append("<span class=\"punctuation\">)</span>").toString());
            }
        });
    }
}