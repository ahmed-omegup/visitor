package lib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import lib.expression.*;

public class IntegerEvaluator implements Visitor<Integer> {
    private final Map<String, Integer> variables;
    private final Map<String, Function<List<Integer>, Integer>> functions;
    private final VariableReferenceExtractor variableReferenceExtractor = new VariableReferenceExtractor();

    IntegerEvaluator() {
        this(Map.of(), Map.of());
    }

    IntegerEvaluator(Map<String, Integer> variables, Map<String, Function<List<Integer>, Integer>> functions) {
        this.variables = variables;
        this.functions = functions;
    }

    public Integer handle(Expression expression) {
        return evaluate(expression);
    }

    private Integer evaluate(Expression expression) {
        return expression.accept(this);
    }

    public Integer visit(Literal expression) {
        try {
            return Integer.parseInt(expression.value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Literal is not an integer: " + expression.value, exception);
        }
    }

    public Integer visit(VariableReference expression) {
        if (!variables.containsKey(expression.name)) {
            throw new IllegalArgumentException("Unknown variable: " + expression.name);
        }
        return variables.get(expression.name);
    }

    public Integer visit(Addition expression) { return evaluate(expression.left) + evaluate(expression.right); }
    public Integer visit(Subtraction expression) { return evaluate(expression.left) - evaluate(expression.right); }
    public Integer visit(Multiplication expression) { return evaluate(expression.left) * evaluate(expression.right); }
    public Integer visit(Division expression) { return evaluate(expression.dividend) / evaluate(expression.divisor); }
    public Integer visit(Negation expression) { return -evaluate(expression.operand); }
    public Integer visit(Modulo expression) { return evaluate(expression.left) % evaluate(expression.right); }
    public Integer visit(Exponentiation expression) { return (int) Math.pow(evaluate(expression.base), evaluate(expression.exponent)); }
    public Integer visit(Equality expression) { return evaluate(expression.left).intValue() == evaluate(expression.right).intValue() ? 1 : 0; }
    public Integer visit(Inequality expression) { return evaluate(expression.left).intValue() != evaluate(expression.right).intValue() ? 1 : 0; }
    public Integer visit(LessThan expression) { return evaluate(expression.left) < evaluate(expression.right) ? 1 : 0; }
    public Integer visit(GreaterThan expression) { return evaluate(expression.left) > evaluate(expression.right) ? 1 : 0; }
    public Integer visit(LessThanOrEqual expression) { return evaluate(expression.left) <= evaluate(expression.right) ? 1 : 0; }
    public Integer visit(GreaterThanOrEqual expression) { return evaluate(expression.left) >= evaluate(expression.right) ? 1 : 0; }
    public Integer visit(Conjunction expression) { return truthy(evaluate(expression.left)) && truthy(evaluate(expression.right)) ? 1 : 0; }
    public Integer visit(Disjunction expression) { return truthy(evaluate(expression.left)) || truthy(evaluate(expression.right)) ? 1 : 0; }
    public Integer visit(LogicalNot expression) { return truthy(evaluate(expression.operand)) ? 0 : 1; }
    public Integer visit(Conditional expression) { return truthy(evaluate(expression.condition)) ? evaluate(expression.whenTrue) : evaluate(expression.whenFalse); }

    public Integer visit(FunctionCall expression) {
        var callee = variableReferenceExtractor.handle(expression.callee, "Function call requires a variable reference callee");
        if (!functions.containsKey(callee.name)) {
            throw new IllegalArgumentException("Unknown function: " + callee.name);
        }
        var arguments = new ArrayList<Integer>();
        for (var argument : expression.arguments) {
            arguments.add(evaluate(argument));
        }
        return functions.get(callee.name).apply(arguments);
    }

    private boolean truthy(int value) {
        return value != 0;
    }
}