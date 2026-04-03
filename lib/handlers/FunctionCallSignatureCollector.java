package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class FunctionCallSignatureCollector implements Visitor<List<String>> {
    FunctionCallSignatureCollector() {}

    private boolean active;
    private boolean labeling;
    private List<String> signatures;
    private String currentLabel;

    public List<String> handle(Expression expression) {
        var signatures = new ArrayList<String>();
        collect(expression, signatures);
        return signatures;
    }

    private void collect(Expression expression, List<String> signatures) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousSignatures = this.signatures;
        this.signatures = signatures;
        expression.accept(this);
        this.signatures = previousSignatures;
        this.active = previousActive;
    }

    private String calleeLabel(Expression expression) {
        boolean previousLabeling = this.labeling;
        String previousLabel = this.currentLabel;
        this.labeling = true;
        expression.accept(this);
        String result = this.currentLabel;
        this.currentLabel = previousLabel;
        this.labeling = previousLabeling;
        return result;
    }

    public List<String> visit(Literal expression) {
        if (labeling) {
            currentLabel = "Literal";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        return null;
    }

    public List<String> visit(VariableReference expression) {
        if (labeling) {
            currentLabel = expression.name;
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        return null;
    }

    public List<String> visit(Addition expression) {
        if (labeling) {
            currentLabel = "Addition";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(Subtraction expression) {
        if (labeling) {
            currentLabel = "Subtraction";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(Multiplication expression) {
        if (labeling) {
            currentLabel = "Multiplication";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(Division expression) {
        if (labeling) {
            currentLabel = "Division";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.dividend, signatures);
        collect(expression.divisor, signatures);
        return null;
    }

    public List<String> visit(Negation expression) {
        if (labeling) {
            currentLabel = "Negation";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.operand, signatures);
        return null;
    }

    public List<String> visit(Modulo expression) {
        if (labeling) {
            currentLabel = "Modulo";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(Exponentiation expression) {
        if (labeling) {
            currentLabel = "Exponentiation";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.base, signatures);
        collect(expression.exponent, signatures);
        return null;
    }

    public List<String> visit(Equality expression) {
        if (labeling) {
            currentLabel = "Equality";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(Inequality expression) {
        if (labeling) {
            currentLabel = "Inequality";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(LessThan expression) {
        if (labeling) {
            currentLabel = "LessThan";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(GreaterThan expression) {
        if (labeling) {
            currentLabel = "GreaterThan";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(LessThanOrEqual expression) {
        if (labeling) {
            currentLabel = "LessThanOrEqual";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(GreaterThanOrEqual expression) {
        if (labeling) {
            currentLabel = "GreaterThanOrEqual";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(Conjunction expression) {
        if (labeling) {
            currentLabel = "Conjunction";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(Disjunction expression) {
        if (labeling) {
            currentLabel = "Disjunction";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.left, signatures);
        collect(expression.right, signatures);
        return null;
    }

    public List<String> visit(LogicalNot expression) {
        if (labeling) {
            currentLabel = "LogicalNot";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.operand, signatures);
        return null;
    }

    public List<String> visit(Conditional expression) {
        if (labeling) {
            currentLabel = "Conditional";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        collect(expression.condition, signatures);
        collect(expression.whenTrue, signatures);
        collect(expression.whenFalse, signatures);
        return null;
    }

    public List<String> visit(FunctionCall expression) {
        if (labeling) {
            currentLabel = "FunctionCall";
            return null;
        }
        if (!active) {
            return handle(expression);
        }
        signatures.add(calleeLabel(expression.callee) + "/" + expression.arguments.length);
        collect(expression.callee, signatures);
        for (var argument : expression.arguments) {
            collect(argument, signatures);
        }
        return null;
    }
}