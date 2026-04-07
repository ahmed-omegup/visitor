package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class RootToLeafTracePrinter extends AbstractExpressionFunction<String> {
    RootToLeafTracePrinter() {}
    private List<String> prefix;
    private List<String> traces;

    public String apply(Expression expression) {
        var traces = new ArrayList<String>();
        collect(expression, new ArrayList<>(), traces);
        return String.join("\n", traces);
    }
    private void collect(Expression expression, List<String> prefix, List<String> traces) {
        List<String> previousPrefix = this.prefix;
        this.prefix = prefix;
        List<String> previousTraces = this.traces;
        this.traces = traces;
        visitExpression(expression);
        this.traces = previousTraces;
        this.prefix = previousPrefix;
    }

    public String visit(Literal expression) { var path = new ArrayList<>(prefix);
        path.add("Literal(" + expression.value + ")");
        traces.add(String.join(" -> ", path));
        return null;
    }
    public String visit(VariableReference expression) { var path = new ArrayList<>(prefix);
        path.add("VariableReference(" + expression.name + ")");
        traces.add(String.join(" -> ", path));
        return null;
    }
    public String visit(Addition expression) { descend("Addition", expression.left, expression.right); return null; }
    public String visit(Subtraction expression) { descend("Subtraction", expression.left, expression.right); return null; }
    public String visit(Multiplication expression) { descend("Multiplication", expression.left, expression.right); return null; }
    public String visit(Division expression) { descend("Division", expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) { descendUnary("Negation", expression.operand); return null; }
    public String visit(Modulo expression) { descend("Modulo", expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) { descend("Exponentiation", expression.base, expression.exponent); return null; }
    public String visit(Equality expression) { descend("Equality", expression.left, expression.right); return null; }
    public String visit(Inequality expression) { descend("Inequality", expression.left, expression.right); return null; }
    public String visit(LessThan expression) { descend("LessThan", expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) { descend("GreaterThan", expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) { descend("LessThanOrEqual", expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { descend("GreaterThanOrEqual", expression.left, expression.right); return null; }
    public String visit(Conjunction expression) { descend("Conjunction", expression.left, expression.right); return null; }
    public String visit(Disjunction expression) { descend("Disjunction", expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) { descendUnary("LogicalNot", expression.operand); return null; }
    public String visit(Conditional expression) { var next = new ArrayList<>(prefix);
        next.add("Conditional");
        collect(expression.condition, next, traces);
        collect(expression.whenTrue, next, traces);
        collect(expression.whenFalse, next, traces);
        return null;
    }
    public String visit(FunctionCall expression) { var next = new ArrayList<>(prefix);
        next.add("FunctionCall");
        collect(expression.callee, next, traces);
        for (var argument : expression.arguments) {
            collect(argument, next, traces);
        }
        return null;
    }

    private void descend(String label, Expression left, Expression right) {
        var next = new ArrayList<>(prefix);
        next.add(label);
        collect(left, next, traces);
        collect(right, next, traces);
    }

    private void descendUnary(String label, Expression operand) {
        var next = new ArrayList<>(prefix);
        next.add(label);
        collect(operand, next, traces);
    }

}