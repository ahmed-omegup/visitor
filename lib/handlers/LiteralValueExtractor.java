package lib.handlers;

import lib.expression.*;

final class LiteralValueExtractor implements Visitor<Integer> {
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
}