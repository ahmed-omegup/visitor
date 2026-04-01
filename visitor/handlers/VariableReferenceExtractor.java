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

final class VariableReferenceExtractor {
    VariableReference handle(Expression expression, String errorMessage) {
        return expression.accept(new Visitor<VariableReference>() {
            public VariableReference visit(Literal expression) {
                throw new IllegalArgumentException(errorMessage);
            }

            public VariableReference visit(VariableReference expression) {
                return expression;
            }

            public VariableReference visit(Addition expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Subtraction expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Multiplication expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Division expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Negation expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Modulo expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Exponentiation expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Equality expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Inequality expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(LessThan expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(GreaterThan expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(LessThanOrEqual expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(GreaterThanOrEqual expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Conjunction expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Disjunction expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(LogicalNot expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(Conditional expression) { throw new IllegalArgumentException(errorMessage); }
            public VariableReference visit(FunctionCall expression) { throw new IllegalArgumentException(errorMessage); }
        });
    }
}