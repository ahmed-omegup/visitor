package lib.expression;

public final class ExpressionFactory {
    private static final port.ExpressionFactory INSTANCE = new DefaultExpressionFactory();

    private ExpressionFactory() {}

    public static port.ExpressionFactory port() {
        return INSTANCE;
    }

    public static Literal literal(String value) {
        return INSTANCE.literal(value);
    }

    public static VariableReference variableReference(String name) {
        return INSTANCE.variableReference(name);
    }

    public static Addition addition(Expression left, Expression right) {
        return INSTANCE.addition(left, right);
    }

    public static Subtraction subtraction(Expression left, Expression right) {
        return INSTANCE.subtraction(left, right);
    }

    public static Multiplication multiplication(Expression left, Expression right) {
        return INSTANCE.multiplication(left, right);
    }

    public static Division division(Expression dividend, Expression divisor) {
        return INSTANCE.division(dividend, divisor);
    }

    public static Negation negation(Expression operand) {
        return INSTANCE.negation(operand);
    }

    public static Modulo modulo(Expression left, Expression right) {
        return INSTANCE.modulo(left, right);
    }

    public static Exponentiation exponentiation(Expression base, Expression exponent) {
        return INSTANCE.exponentiation(base, exponent);
    }

    public static Equality equality(Expression left, Expression right) {
        return INSTANCE.equality(left, right);
    }

    public static Inequality inequality(Expression left, Expression right) {
        return INSTANCE.inequality(left, right);
    }

    public static LessThan lessThan(Expression left, Expression right) {
        return INSTANCE.lessThan(left, right);
    }

    public static GreaterThan greaterThan(Expression left, Expression right) {
        return INSTANCE.greaterThan(left, right);
    }

    public static LessThanOrEqual lessThanOrEqual(Expression left, Expression right) {
        return INSTANCE.lessThanOrEqual(left, right);
    }

    public static GreaterThanOrEqual greaterThanOrEqual(Expression left, Expression right) {
        return INSTANCE.greaterThanOrEqual(left, right);
    }

    public static Conjunction conjunction(Expression left, Expression right) {
        return INSTANCE.conjunction(left, right);
    }

    public static Disjunction disjunction(Expression left, Expression right) {
        return INSTANCE.disjunction(left, right);
    }

    public static LogicalNot logicalNot(Expression operand) {
        return INSTANCE.logicalNot(operand);
    }

    public static Conditional conditional(Expression condition, Expression whenTrue, Expression whenFalse) {
        return INSTANCE.conditional(condition, whenTrue, whenFalse);
    }

    public static FunctionCall functionCall(Expression callee, Expression... arguments) {
        return INSTANCE.functionCall(callee, arguments);
    }

    private static final class DefaultExpressionFactory implements port.ExpressionFactory {
        public Literal literal(String value) {
            return new Literal(value);
        }

        public VariableReference variableReference(String name) {
            return new VariableReference(name);
        }

        public Addition addition(Expression left, Expression right) {
            return new Addition(left, right);
        }

        public Subtraction subtraction(Expression left, Expression right) {
            return new Subtraction(left, right);
        }

        public Multiplication multiplication(Expression left, Expression right) {
            return new Multiplication(left, right);
        }

        public Division division(Expression dividend, Expression divisor) {
            return new Division(dividend, divisor);
        }

        public Negation negation(Expression operand) {
            return new Negation(operand);
        }

        public Modulo modulo(Expression left, Expression right) {
            return new Modulo(left, right);
        }

        public Exponentiation exponentiation(Expression base, Expression exponent) {
            return new Exponentiation(base, exponent);
        }

        public Equality equality(Expression left, Expression right) {
            return new Equality(left, right);
        }

        public Inequality inequality(Expression left, Expression right) {
            return new Inequality(left, right);
        }

        public LessThan lessThan(Expression left, Expression right) {
            return new LessThan(left, right);
        }

        public GreaterThan greaterThan(Expression left, Expression right) {
            return new GreaterThan(left, right);
        }

        public LessThanOrEqual lessThanOrEqual(Expression left, Expression right) {
            return new LessThanOrEqual(left, right);
        }

        public GreaterThanOrEqual greaterThanOrEqual(Expression left, Expression right) {
            return new GreaterThanOrEqual(left, right);
        }

        public Conjunction conjunction(Expression left, Expression right) {
            return new Conjunction(left, right);
        }

        public Disjunction disjunction(Expression left, Expression right) {
            return new Disjunction(left, right);
        }

        public LogicalNot logicalNot(Expression operand) {
            return new LogicalNot(operand);
        }

        public Conditional conditional(Expression condition, Expression whenTrue, Expression whenFalse) {
            return new Conditional(condition, whenTrue, whenFalse);
        }

        public FunctionCall functionCall(Expression callee, Expression... arguments) {
            return new FunctionCall(callee, arguments);
        }
    }
}