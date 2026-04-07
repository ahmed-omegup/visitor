package lib.handlers;

import lib.expression.*;

final class VariableReferenceExtractor implements Visitor<VariableReference> {
    private final String errorMessage;

    VariableReferenceExtractor() {
        this("expected variable");
    }

    VariableReferenceExtractor(String errorMessage) {
        this.errorMessage = errorMessage;
    }

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
}