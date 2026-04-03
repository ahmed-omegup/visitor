package lib.visitors;

import lib.expression.*;

public class IndentedTracePrinter implements Visitor<Void> {
    IndentedTracePrinter() {}

    private boolean active;
    private int depth;

    public void handle(Expression expression) {
        print(expression, 0);
    }

    private void print(Expression expression, int depth) {
        boolean previousActive = this.active;
        int previousDepth = this.depth;
        this.active = true;
        this.depth = depth;
        expression.accept(this);
        this.depth = previousDepth;
        this.active = previousActive;
    }

    private void line(String value) {
        System.out.println("  ".repeat(depth) + value);
    }

    public Void visit(Literal expression) { if (!active) { handle(expression); return null; } line("Literal(" + expression.value + ")"); return null; }
    public Void visit(VariableReference expression) { if (!active) { handle(expression); return null; } line("VariableReference(" + expression.name + ")"); return null; }
    public Void visit(Addition expression) { if (!active) { handle(expression); return null; } line("Addition"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(Subtraction expression) { if (!active) { handle(expression); return null; } line("Subtraction"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(Multiplication expression) { if (!active) { handle(expression); return null; } line("Multiplication"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(Division expression) { if (!active) { handle(expression); return null; } line("Division"); print(expression.dividend, depth + 1); print(expression.divisor, depth + 1); return null; }
    public Void visit(Negation expression) { if (!active) { handle(expression); return null; } line("Negation"); print(expression.operand, depth + 1); return null; }
    public Void visit(Modulo expression) { if (!active) { handle(expression); return null; } line("Modulo"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(Exponentiation expression) { if (!active) { handle(expression); return null; } line("Exponentiation"); print(expression.base, depth + 1); print(expression.exponent, depth + 1); return null; }
    public Void visit(Equality expression) { if (!active) { handle(expression); return null; } line("Equality"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(Inequality expression) { if (!active) { handle(expression); return null; } line("Inequality"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(LessThan expression) { if (!active) { handle(expression); return null; } line("LessThan"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(GreaterThan expression) { if (!active) { handle(expression); return null; } line("GreaterThan"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(LessThanOrEqual expression) { if (!active) { handle(expression); return null; } line("LessThanOrEqual"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(GreaterThanOrEqual expression) { if (!active) { handle(expression); return null; } line("GreaterThanOrEqual"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(Conjunction expression) { if (!active) { handle(expression); return null; } line("Conjunction"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(Disjunction expression) { if (!active) { handle(expression); return null; } line("Disjunction"); print(expression.left, depth + 1); print(expression.right, depth + 1); return null; }
    public Void visit(LogicalNot expression) { if (!active) { handle(expression); return null; } line("LogicalNot"); print(expression.operand, depth + 1); return null; }
    public Void visit(Conditional expression) { if (!active) { handle(expression); return null; } line("Conditional"); print(expression.condition, depth + 1); print(expression.whenTrue, depth + 1); print(expression.whenFalse, depth + 1); return null; }

    public Void visit(FunctionCall expression) {
        if (!active) {
            handle(expression);
            return null;
        }
        line("FunctionCall");
        print(expression.callee, depth + 1);
        for (var argument : expression.arguments) {
            print(argument, depth + 1);
        }
        return null;
    }
}