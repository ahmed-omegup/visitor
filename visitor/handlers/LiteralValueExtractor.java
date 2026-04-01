package visitor.handlers;

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