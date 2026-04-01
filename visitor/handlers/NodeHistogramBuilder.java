package visitor.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Disjunction;
import visitor.expression.Division;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.Expression;
import visitor.expression.FunctionCall;
import visitor.expression.GreaterThan;
import visitor.expression.GreaterThanOrEqual;
import visitor.expression.Inequality;
import visitor.expression.LessThan;
import visitor.expression.LessThanOrEqual;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Modulo;
import visitor.expression.Multiplication;
import visitor.expression.Negation;
import visitor.expression.Subtraction;
import visitor.expression.VariableReference;
import visitor.expression.Visitor;

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