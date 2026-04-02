package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class BinaryOperatorLabelCollector {
    public List<String> handle(Expression expression) {
        var labels = new ArrayList<String>();
        collect(expression, labels);
        return labels;
    }

    private void collect(Expression expression, List<String> labels) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { labels.add("Addition"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Subtraction expression) { labels.add("Subtraction"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Multiplication expression) { labels.add("Multiplication"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Division expression) { labels.add("Division"); collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
            public Void visit(Negation expression) { collect(expression.operand, labels); return null; }
            public Void visit(Modulo expression) { labels.add("Modulo"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Exponentiation expression) { labels.add("Exponentiation"); collect(expression.base, labels); collect(expression.exponent, labels); return null; }
            public Void visit(Equality expression) { labels.add("Equality"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Inequality expression) { labels.add("Inequality"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(LessThan expression) { labels.add("LessThan"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(GreaterThan expression) { labels.add("GreaterThan"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(LessThanOrEqual expression) { labels.add("LessThanOrEqual"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(GreaterThanOrEqual expression) { labels.add("GreaterThanOrEqual"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Conjunction expression) { labels.add("Conjunction"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Disjunction expression) { labels.add("Disjunction"); collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, labels); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, labels);
                for (var argument : expression.arguments) {
                    collect(argument, labels);
                }
                return null;
            }
        });
    }
}