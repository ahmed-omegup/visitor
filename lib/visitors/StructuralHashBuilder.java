package lib.visitors;

import java.util.Objects;

import lib.expression.*;

public class StructuralHashBuilder extends AbstractExpressionFunction<Integer> {
    StructuralHashBuilder() {}

    public Integer apply(Expression expression) {
        return hash(expression);
    }

    private Integer hash(Expression expression) {
        return visitExpression(expression);
    }

    public Integer visit(Literal expression) { return Objects.hash("Literal"); }
    public Integer visit(VariableReference expression) { return Objects.hash("VariableReference"); }
    public Integer visit(Addition expression) { return Objects.hash("Addition", hash(expression.left), hash(expression.right)); }
    public Integer visit(Subtraction expression) { return Objects.hash("Subtraction", hash(expression.left), hash(expression.right)); }
    public Integer visit(Multiplication expression) { return Objects.hash("Multiplication", hash(expression.left), hash(expression.right)); }
    public Integer visit(Division expression) { return Objects.hash("Division", hash(expression.dividend), hash(expression.divisor)); }
    public Integer visit(Negation expression) { return Objects.hash("Negation", hash(expression.operand)); }
    public Integer visit(Modulo expression) { return Objects.hash("Modulo", hash(expression.left), hash(expression.right)); }
    public Integer visit(Exponentiation expression) { return Objects.hash("Exponentiation", hash(expression.base), hash(expression.exponent)); }
    public Integer visit(Equality expression) { return Objects.hash("Equality", hash(expression.left), hash(expression.right)); }
    public Integer visit(Inequality expression) { return Objects.hash("Inequality", hash(expression.left), hash(expression.right)); }
    public Integer visit(LessThan expression) { return Objects.hash("LessThan", hash(expression.left), hash(expression.right)); }
    public Integer visit(GreaterThan expression) { return Objects.hash("GreaterThan", hash(expression.left), hash(expression.right)); }
    public Integer visit(LessThanOrEqual expression) { return Objects.hash("LessThanOrEqual", hash(expression.left), hash(expression.right)); }
    public Integer visit(GreaterThanOrEqual expression) { return Objects.hash("GreaterThanOrEqual", hash(expression.left), hash(expression.right)); }
    public Integer visit(Conjunction expression) { return Objects.hash("Conjunction", hash(expression.left), hash(expression.right)); }
    public Integer visit(Disjunction expression) { return Objects.hash("Disjunction", hash(expression.left), hash(expression.right)); }
    public Integer visit(LogicalNot expression) { return Objects.hash("LogicalNot", hash(expression.operand)); }

    public Integer visit(Conditional expression) {
        return Objects.hash("Conditional", hash(expression.condition), hash(expression.whenTrue), hash(expression.whenFalse));
    }

    public Integer visit(FunctionCall expression) {
        var values = new Object[expression.arguments.size() + 2];
        values[0] = "FunctionCall";
        values[1] = hash(expression.callee);
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            values[index + 2] = hash(iter.next());
        }
        return Objects.hash(values);
    }
}