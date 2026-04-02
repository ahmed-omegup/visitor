package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

import visitor.expression.*;

public class FunctionArgumentRootCollector {
    public List<String> handle(Expression expression) {
        var labels = new ArrayList<String>();
        collect(expression, labels);
        return labels;
    }

    private void collect(Expression expression, List<String> labels) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Division expression) { collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
            public Void visit(Negation expression) { collect(expression.operand, labels); return null; }
            public Void visit(Modulo expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, labels); collect(expression.exponent, labels); return null; }
            public Void visit(Equality expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Inequality expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(LessThan expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, labels); collect(expression.right, labels); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, labels); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }
            public Void visit(FunctionCall expression) {
                for (var argument : expression.arguments) {
                    labels.add(label(argument));
                }
                collect(expression.callee, labels);
                for (var argument : expression.arguments) {
                    collect(argument, labels);
                }
                return null;
            }
        });
    }

    private String label(Expression expression) {
        return expression.accept(new Visitor<String>() {
            public String visit(Literal expression) { return "Literal"; }
            public String visit(VariableReference expression) { return "VariableReference"; }
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