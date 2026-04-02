package lib.handlers;

import java.util.ArrayList;
import java.util.List;

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