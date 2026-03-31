package visitor.expression;

public interface Visitor<R> {
    R visit(Literal e);
    R visit(VariableReference e);
    R visit(Addition e);
    R visit(Subtraction e);
    R visit(Multiplication e);
    R visit(Division e);
    R visit(Negation e);
    R visit(Modulo e);
    R visit(Exponentiation e);
    R visit(Equality e);
    R visit(Inequality e);
    R visit(LessThan e);
    R visit(GreaterThan e);
    R visit(LessThanOrEqual e);
    R visit(GreaterThanOrEqual e);
    R visit(Conjunction e);
    R visit(Disjunction e);
    R visit(LogicalNot e);
    R visit(Conditional e);
    R visit(FunctionCall e);
}
