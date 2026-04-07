package lib.legacy;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class DepthAnnotatedPreorderPrinter extends AbstractExpressionFunction<String> {
    DepthAnnotatedPreorderPrinter() {}
    private int depth;
    private List<String> lines;

    public String apply(Expression expression) {
        var lines = new ArrayList<String>();
        collect(expression, 0, lines);
        return String.join("\n", lines);
    }
    private void collect(Expression expression, int depth, List<String> lines) {
        int previousDepth = this.depth;
        this.depth = depth;
        List<String> previousLines = this.lines;
        this.lines = lines;
        visitExpression(expression);
        this.lines = previousLines;
        this.depth = previousDepth;
    }

    public String visit(Literal expression) { lines.add(depth + ": Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { lines.add(depth + ": VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { lines.add(depth + ": Addition"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Subtraction expression) { lines.add(depth + ": Subtraction"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Multiplication expression) { lines.add(depth + ": Multiplication"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Division expression) { lines.add(depth + ": Division"); collect(expression.dividend, depth + 1, lines); collect(expression.divisor, depth + 1, lines); return null; }
    public String visit(Negation expression) { lines.add(depth + ": Negation"); collect(expression.operand, depth + 1, lines); return null; }
    public String visit(Modulo expression) { lines.add(depth + ": Modulo"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Exponentiation expression) { lines.add(depth + ": Exponentiation"); collect(expression.base, depth + 1, lines); collect(expression.exponent, depth + 1, lines); return null; }
    public String visit(Equality expression) { lines.add(depth + ": Equality"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Inequality expression) { lines.add(depth + ": Inequality"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(LessThan expression) { lines.add(depth + ": LessThan"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(GreaterThan expression) { lines.add(depth + ": GreaterThan"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(LessThanOrEqual expression) { lines.add(depth + ": LessThanOrEqual"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(GreaterThanOrEqual expression) { lines.add(depth + ": GreaterThanOrEqual"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Conjunction expression) { lines.add(depth + ": Conjunction"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(Disjunction expression) { lines.add(depth + ": Disjunction"); collect(expression.left, depth + 1, lines); collect(expression.right, depth + 1, lines); return null; }
    public String visit(LogicalNot expression) { lines.add(depth + ": LogicalNot"); collect(expression.operand, depth + 1, lines); return null; }
    public String visit(Conditional expression) { lines.add(depth + ": Conditional"); collect(expression.condition, depth + 1, lines); collect(expression.whenTrue, depth + 1, lines); collect(expression.whenFalse, depth + 1, lines); return null; }
    public String visit(FunctionCall expression) { lines.add(depth + ": FunctionCall");
        collect(expression.callee, depth + 1, lines);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, lines);
        }
        return null;
    }

}