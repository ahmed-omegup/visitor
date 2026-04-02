package visitor.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import visitor.expression.*;

public class LiteralFrequencyBuilder {
    public Map<String, Integer> handle(Expression expression) {
        var frequencies = new LinkedHashMap<String, Integer>();
        collect(expression, frequencies);
        return frequencies;
    }

    private void collect(Expression expression, Map<String, Integer> frequencies) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { frequencies.merge(expression.value, 1, Integer::sum); return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(Division expression) { collect(expression.dividend, frequencies); collect(expression.divisor, frequencies); return null; }
            public Void visit(Negation expression) { collect(expression.operand, frequencies); return null; }
            public Void visit(Modulo expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, frequencies); collect(expression.exponent, frequencies); return null; }
            public Void visit(Equality expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(Inequality expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(LessThan expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, frequencies); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, frequencies); collect(expression.whenTrue, frequencies); collect(expression.whenFalse, frequencies); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, frequencies);
                for (var argument : expression.arguments) {
                    collect(argument, frequencies);
                }
                return null;
            }
        });
    }
}