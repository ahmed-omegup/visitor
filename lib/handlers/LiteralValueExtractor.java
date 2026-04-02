package lib.handlers;

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

final class LiteralValueExtractor {
    Integer handle(Expression expression) {
        return expression.accept(new Visitor<Integer>() {
            public Integer visit(Literal expression) {
                try {
                    return Integer.parseInt(expression.value);
                } catch (NumberFormatException exception) {
                    return null;
                }
            }

            public Integer visit(VariableReference expression) { return null; }
            public Integer visit(Addition expression) { return null; }
            public Integer visit(Subtraction expression) { return null; }
            public Integer visit(Multiplication expression) { return null; }
            public Integer visit(Division expression) { return null; }
            public Integer visit(Negation expression) { return null; }
            public Integer visit(Modulo expression) { return null; }
            public Integer visit(Exponentiation expression) { return null; }
            public Integer visit(Equality expression) { return null; }
            public Integer visit(Inequality expression) { return null; }
            public Integer visit(LessThan expression) { return null; }
            public Integer visit(GreaterThan expression) { return null; }
            public Integer visit(LessThanOrEqual expression) { return null; }
            public Integer visit(GreaterThanOrEqual expression) { return null; }
            public Integer visit(Conjunction expression) { return null; }
            public Integer visit(Disjunction expression) { return null; }
            public Integer visit(LogicalNot expression) { return null; }
            public Integer visit(Conditional expression) { return null; }
            public Integer visit(FunctionCall expression) { return null; }
        });
    }
}