package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class OperatorHistogramBuilder {
    public Map<String, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<String, Integer>();
        collect(expression, histogram);
        return histogram;
    }

    private void collect(Expression expression, Map<String, Integer> histogram) {
        expression.accept(new Visitor<Void>() {
            private void hit(String type) {
                histogram.merge(type, 1, Integer::sum);
            }

            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { hit("Addition"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Subtraction expression) { hit("Subtraction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Multiplication expression) { hit("Multiplication"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Division expression) { hit("Division"); collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
            public Void visit(Negation expression) { hit("Negation"); collect(expression.operand, histogram); return null; }
            public Void visit(Modulo expression) { hit("Modulo"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Exponentiation expression) { hit("Exponentiation"); collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
            public Void visit(Equality expression) { hit("Equality"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Inequality expression) { hit("Inequality"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LessThan expression) { hit("LessThan"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(GreaterThan expression) { hit("GreaterThan"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LessThanOrEqual expression) { hit("LessThanOrEqual"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(GreaterThanOrEqual expression) { hit("GreaterThanOrEqual"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Conjunction expression) { hit("Conjunction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(Disjunction expression) { hit("Disjunction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
            public Void visit(LogicalNot expression) { hit("LogicalNot"); collect(expression.operand, histogram); return null; }
            public Void visit(Conditional expression) { hit("Conditional"); collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
            public Void visit(FunctionCall expression) {
                hit("FunctionCall");
                collect(expression.callee, histogram);
                for (var argument : expression.arguments) {
                    collect(argument, histogram);
                }
                return null;
            }
        });
    }
}