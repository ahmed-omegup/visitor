package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class EvaluationOrderListBuilder {
    public List<String> handle(Expression expression) {
        var steps = new ArrayList<String>();
        append(expression, steps);
        return steps;
    }

    private void append(Expression expression, List<String> steps) {
        expression.accept(new Visitor<Void>() {
            private void step(String value) {
                steps.add(value);
            }

            public Void visit(Literal expression) { step("Literal(" + expression.value + ")"); return null; }
            public Void visit(VariableReference expression) { step("VariableReference(" + expression.name + ")"); return null; }
            public Void visit(Addition expression) { step("Addition"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(Subtraction expression) { step("Subtraction"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(Multiplication expression) { step("Multiplication"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(Division expression) { step("Division"); append(expression.dividend, steps); append(expression.divisor, steps); return null; }
            public Void visit(Negation expression) { step("Negation"); append(expression.operand, steps); return null; }
            public Void visit(Modulo expression) { step("Modulo"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(Exponentiation expression) { step("Exponentiation"); append(expression.base, steps); append(expression.exponent, steps); return null; }
            public Void visit(Equality expression) { step("Equality"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(Inequality expression) { step("Inequality"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(LessThan expression) { step("LessThan"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(GreaterThan expression) { step("GreaterThan"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(LessThanOrEqual expression) { step("LessThanOrEqual"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(GreaterThanOrEqual expression) { step("GreaterThanOrEqual"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(Conjunction expression) { step("Conjunction"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(Disjunction expression) { step("Disjunction"); append(expression.left, steps); append(expression.right, steps); return null; }
            public Void visit(LogicalNot expression) { step("LogicalNot"); append(expression.operand, steps); return null; }
            public Void visit(Conditional expression) { step("Conditional"); append(expression.condition, steps); append(expression.whenTrue, steps); append(expression.whenFalse, steps); return null; }
            public Void visit(FunctionCall expression) {
                step("FunctionCall");
                append(expression.callee, steps);
                for (var argument : expression.arguments) {
                    append(argument, steps);
                }
                return null;
            }
        });
    }
}