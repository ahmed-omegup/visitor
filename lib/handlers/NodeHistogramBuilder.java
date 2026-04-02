package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

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

public class NodeHistogramBuilder {
    public Map<String, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<String, Integer>();
        populate(expression, histogram);
        return histogram;
    }

    private void populate(Expression expression, Map<String, Integer> histogram) {
        expression.accept(new Visitor<Void>() {
            private void hit(String type) {
                histogram.merge(type, 1, Integer::sum);
            }

            public Void visit(Literal expression) { hit("Literal"); return null; }
            public Void visit(VariableReference expression) { hit("VariableReference"); return null; }
            public Void visit(Addition expression) { hit("Addition"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(Subtraction expression) { hit("Subtraction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(Multiplication expression) { hit("Multiplication"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(Division expression) { hit("Division"); populate(expression.dividend, histogram); populate(expression.divisor, histogram); return null; }
            public Void visit(Negation expression) { hit("Negation"); populate(expression.operand, histogram); return null; }
            public Void visit(Modulo expression) { hit("Modulo"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(Exponentiation expression) { hit("Exponentiation"); populate(expression.base, histogram); populate(expression.exponent, histogram); return null; }
            public Void visit(Equality expression) { hit("Equality"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(Inequality expression) { hit("Inequality"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(LessThan expression) { hit("LessThan"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(GreaterThan expression) { hit("GreaterThan"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(LessThanOrEqual expression) { hit("LessThanOrEqual"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(GreaterThanOrEqual expression) { hit("GreaterThanOrEqual"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(Conjunction expression) { hit("Conjunction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(Disjunction expression) { hit("Disjunction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
            public Void visit(LogicalNot expression) { hit("LogicalNot"); populate(expression.operand, histogram); return null; }
            public Void visit(Conditional expression) { hit("Conditional"); populate(expression.condition, histogram); populate(expression.whenTrue, histogram); populate(expression.whenFalse, histogram); return null; }
            public Void visit(FunctionCall expression) {
                hit("FunctionCall");
                populate(expression.callee, histogram);
                for (var argument : expression.arguments) {
                    populate(argument, histogram);
                }
                return null;
            }
        });
    }
}