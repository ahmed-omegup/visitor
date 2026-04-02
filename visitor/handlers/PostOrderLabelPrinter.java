package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class PostOrderLabelPrinter {
    public String handle(Expression expression) {
        var labels = new ArrayList<String>();
        collect(expression, labels);
        return String.join(" -> ", labels);
    }

    private void collect(Expression expression, List<String> labels) {
        expression.accept(new Visitor<Void>() {
            private void label(String value) {
                labels.add(value);
            }

            public Void visit(Literal expression) { label("Literal(" + expression.value + ")"); return null; }
            public Void visit(VariableReference expression) { label("VariableReference(" + expression.name + ")"); return null; }
            public Void visit(Addition expression) { collect(expression.left, labels); collect(expression.right, labels); label("Addition"); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, labels); collect(expression.right, labels); label("Subtraction"); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, labels); collect(expression.right, labels); label("Multiplication"); return null; }
            public Void visit(Division expression) { collect(expression.dividend, labels); collect(expression.divisor, labels); label("Division"); return null; }
            public Void visit(Negation expression) { collect(expression.operand, labels); label("Negation"); return null; }
            public Void visit(Modulo expression) { collect(expression.left, labels); collect(expression.right, labels); label("Modulo"); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, labels); collect(expression.exponent, labels); label("Exponentiation"); return null; }
            public Void visit(Equality expression) { collect(expression.left, labels); collect(expression.right, labels); label("Equality"); return null; }
            public Void visit(Inequality expression) { collect(expression.left, labels); collect(expression.right, labels); label("Inequality"); return null; }
            public Void visit(LessThan expression) { collect(expression.left, labels); collect(expression.right, labels); label("LessThan"); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, labels); collect(expression.right, labels); label("GreaterThan"); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); label("LessThanOrEqual"); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); label("GreaterThanOrEqual"); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, labels); collect(expression.right, labels); label("Conjunction"); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, labels); collect(expression.right, labels); label("Disjunction"); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, labels); label("LogicalNot"); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); label("Conditional"); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, labels);
                for (var argument : expression.arguments) {
                    collect(argument, labels);
                }
                label("FunctionCall");
                return null;
            }
        });
    }
}