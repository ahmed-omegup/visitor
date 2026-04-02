package lib.handlers;

import java.util.LinkedHashSet;
import java.util.Set;

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

public class VariableCollector {
    public Set<String> handle(Expression expression) {
        var names = new LinkedHashSet<String>();
        collect(expression, names);
        return names;
    }

    private void collect(Expression expression, Set<String> names) {
        expression.accept(new Visitor<Void>() {
            public Void visit(Literal expression) { return null; }
            public Void visit(VariableReference expression) { names.add(expression.name); return null; }
            public Void visit(Addition expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Subtraction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Multiplication expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Division expression) { collect(expression.dividend, names); collect(expression.divisor, names); return null; }
            public Void visit(Negation expression) { collect(expression.operand, names); return null; }
            public Void visit(Modulo expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Exponentiation expression) { collect(expression.base, names); collect(expression.exponent, names); return null; }
            public Void visit(Equality expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Inequality expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(LessThan expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(GreaterThan expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(LessThanOrEqual expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(GreaterThanOrEqual expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Conjunction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(Disjunction expression) { collect(expression.left, names); collect(expression.right, names); return null; }
            public Void visit(LogicalNot expression) { collect(expression.operand, names); return null; }
            public Void visit(Conditional expression) { collect(expression.condition, names); collect(expression.whenTrue, names); collect(expression.whenFalse, names); return null; }
            public Void visit(FunctionCall expression) {
                collect(expression.callee, names);
                for (var argument : expression.arguments) {
                    collect(argument, names);
                }
                return null;
            }
        });
    }
}