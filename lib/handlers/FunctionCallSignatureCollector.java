package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class FunctionCallSignatureCollector {
    public List<String> handle(Expression expression) {
        var signatures = new ArrayList<String>();
        collect(expression, signatures);
        return signatures;
    }

    private void collect(Expression expression, List<String> signatures) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(Division expression) { collect(expression.dividend, signatures); collect(expression.divisor, signatures); return null; }
            public Void visit(Negation expression) { collect(expression.operand, signatures); return null; }
            public Void visit(Modulo expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, signatures); collect(expression.exponent, signatures); return null; }
            public Void visit(Equality expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(Inequality expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(LessThan expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, signatures); collect(expression.right, signatures); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, signatures); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, signatures); collect(expression.whenTrue, signatures); collect(expression.whenFalse, signatures); return null; }
            public Void visit(FunctionCall expression) {
                signatures.add(calleeLabel(expression.callee) + "/" + expression.arguments.length);
                collect(expression.callee, signatures);
                for (var argument : expression.arguments) {
                    collect(argument, signatures);
                }
                return null;
            }
        });
    }

    private String calleeLabel(Expression expression) {
        return expression.accept(new Visitor<String>() {
            public String visit(Literal expression) { return "Literal"; }
            public String visit(VariableReference expression) { return expression.name; }
            public String visit(Addition expression) { return "Addition"; }
            public String visit(Subtraction expression) { return "Subtraction"; }
            public String visit(Multiplication expression) { return "Multiplication"; }
            public String visit(Division expression) { return "Division"; }
            public String visit(Negation expression) { return "Negation"; }
            public String visit(Modulo expression) { return "Modulo"; }
            public String visit(Exponentiation expression) { return "Exponentiation"; }
            public String visit(Equality expression) { return "Equality"; }
            public String visit(Inequality expression) { return "Inequality"; }
            public String visit(LessThan expression) { return "LessThan"; }
            public String visit(GreaterThan expression) { return "GreaterThan"; }
            public String visit(LessThanOrEqual expression) { return "LessThanOrEqual"; }
            public String visit(GreaterThanOrEqual expression) { return "GreaterThanOrEqual"; }
            public String visit(Conjunction expression) { return "Conjunction"; }
            public String visit(Disjunction expression) { return "Disjunction"; }
            public String visit(LogicalNot expression) { return "LogicalNot"; }
            public String visit(Conditional expression) { return "Conditional"; }
            public String visit(FunctionCall expression) { return "FunctionCall"; }
        });
    }
}