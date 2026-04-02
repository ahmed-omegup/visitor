package lib.handlers;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class NodeTypeCollector {
    public Set<String> handle(Expression expression) {
        var types = new LinkedHashSet<String>();
        collect(expression, types);
        return types;
    }

    private void collect(Expression expression, Set<String> types) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { types.add("Literal"); return null; }
            public Void visit(VariableReference expression) { types.add("VariableReference"); return null; }
            public Void visit(Addition expression) { types.add("Addition"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(Subtraction expression) { types.add("Subtraction"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(Multiplication expression) { types.add("Multiplication"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(Division expression) { types.add("Division"); collect(expression.dividend, types); collect(expression.divisor, types); return null; }
            public Void visit(Negation expression) { types.add("Negation"); collect(expression.operand, types); return null; }
            public Void visit(Modulo expression) { types.add("Modulo"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(Exponentiation expression) { types.add("Exponentiation"); collect(expression.base, types); collect(expression.exponent, types); return null; }
            public Void visit(Equality expression) { types.add("Equality"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(Inequality expression) { types.add("Inequality"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(LessThan expression) { types.add("LessThan"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(GreaterThan expression) { types.add("GreaterThan"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(LessThanOrEqual expression) { types.add("LessThanOrEqual"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(GreaterThanOrEqual expression) { types.add("GreaterThanOrEqual"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(Conjunction expression) { types.add("Conjunction"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(Disjunction expression) { types.add("Disjunction"); collect(expression.left, types); collect(expression.right, types); return null; }
            public Void visit(LogicalNot expression) { types.add("LogicalNot"); collect(expression.operand, types); return null; }
            public Void visit(Conditional expression) { types.add("Conditional"); collect(expression.condition, types); collect(expression.whenTrue, types); collect(expression.whenFalse, types); return null; }
            public Void visit(FunctionCall expression) {
                types.add("FunctionCall");
                collect(expression.callee, types);
                for (var argument : expression.arguments) {
                    collect(argument, types);
                }
                return null;
            }
        });
    }
}