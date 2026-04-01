package visitor.handlers;

import java.util.ArrayList;
import java.util.List;

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

public class LiteralCollector {
    public List<String> handle(Expression expression) {
        var literals = new ArrayList<String>();
        collect(expression, literals);
        return literals;
    }

    private void collect(Expression expression, List<String> literals) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { literals.add(expression.value); return null; }
            public Void visit(VariableReference expression) { return null; }
            public Void visit(Addition expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(Division expression) { collect(expression.dividend, literals); collect(expression.divisor, literals); return null; }
            public Void visit(Negation expression) { collect(expression.operand, literals); return null; }
            public Void visit(Modulo expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, literals); collect(expression.exponent, literals); return null; }
            public Void visit(Equality expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(Inequality expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(LessThan expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, literals); collect(expression.right, literals); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, literals); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, literals); collect(expression.whenTrue, literals); collect(expression.whenFalse, literals); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, literals);
                for (var argument : expression.arguments) {
                    collect(argument, literals);
                }
                return null;
            }
        });
    }
}