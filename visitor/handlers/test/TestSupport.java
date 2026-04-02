package visitor.handlers.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Disjunction;
import visitor.expression.Division;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.Expression;
import visitor.expression.FunctionCall;
import visitor.expression.GreaterThan;
import visitor.expression.GreaterThanOrEqual;
import visitor.expression.Inequality;
import visitor.expression.LessThan;
import visitor.expression.LessThanOrEqual;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Modulo;
import visitor.expression.Multiplication;
import visitor.expression.Negation;
import visitor.expression.Subtraction;
import visitor.expression.VariableReference;

final class TestSupport {
    private TestSupport() {}

    static Expression sampleTraversalExpression() {
        return new Conditional(
            new Conjunction(
                new LessThan(new VariableReference("x"), new Literal("10")),
                new LogicalNot(new Equality(new Literal("1"), new Literal("0")))
            ),
            new Addition(
                new Subtraction(new Literal("7"), new Literal("2")),
                new Multiplication(
                    new Division(new Literal("8"), new Literal("2")),
                    new Modulo(new Literal("9"), new Literal("4"))
                )
            ),
            new FunctionCall(
                new VariableReference("f"),
                new Exponentiation(new Literal("2"), new Literal("3")),
                new Inequality(new Literal("5"), new Literal("6")),
                new GreaterThan(new Literal("7"), new Literal("1")),
                new LessThanOrEqual(new Literal("2"), new Literal("2")),
                new GreaterThanOrEqual(new Literal("3"), new Literal("3")),
                new Disjunction(new Literal("0"), new Literal("1")),
                new Negation(new Literal("4"))
            )
        );
    }

    static Object newPackagePrivateInstance(String className) {
        try {
            Class<?> type = Class.forName(className);
            Constructor<?> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError("failed to create " + className, exception);
        }
    }

    static Object invokeHandle(Object target, Expression expression) {
        try {
            Method method = target.getClass().getDeclaredMethod("handle", Expression.class);
            method.setAccessible(true);
            return method.invoke(target, expression);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            if (cause instanceof Error error) {
                throw error;
            }
            throw new AssertionError("handle invocation failed", cause);
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError("failed to invoke handle", exception);
        }
    }

    static Object invokeHandleWithMessage(Object target, Expression expression, String message) {
        try {
            Method method = target.getClass().getDeclaredMethod("handle", Expression.class, String.class);
            method.setAccessible(true);
            return method.invoke(target, expression, message);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            if (cause instanceof Error error) {
                throw error;
            }
            throw new AssertionError("handle invocation failed", cause);
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError("failed to invoke handle", exception);
        }
    }

    static List<Expression> sampleNonVariableExpressions() {
        var expressions = new ArrayList<Expression>();
        expressions.add(new Addition(new Literal("1"), new Literal("2")));
        expressions.add(new Subtraction(new Literal("3"), new Literal("1")));
        expressions.add(new Multiplication(new Literal("2"), new Literal("3")));
        expressions.add(new Division(new Literal("6"), new Literal("2")));
        expressions.add(new Negation(new Literal("3")));
        expressions.add(new Modulo(new Literal("7"), new Literal("3")));
        expressions.add(new Exponentiation(new Literal("2"), new Literal("3")));
        expressions.add(new Equality(new Literal("1"), new Literal("1")));
        expressions.add(new Inequality(new Literal("1"), new Literal("2")));
        expressions.add(new LessThan(new Literal("1"), new Literal("2")));
        expressions.add(new GreaterThan(new Literal("2"), new Literal("1")));
        expressions.add(new LessThanOrEqual(new Literal("2"), new Literal("2")));
        expressions.add(new GreaterThanOrEqual(new Literal("2"), new Literal("2")));
        expressions.add(new Conjunction(new Literal("1"), new Literal("1")));
        expressions.add(new Disjunction(new Literal("0"), new Literal("1")));
        expressions.add(new LogicalNot(new Literal("0")));
        expressions.add(new Conditional(new Literal("1"), new Literal("2"), new Literal("3")));
        expressions.add(new FunctionCall(new VariableReference("sum"), new Literal("1"), new Literal("2")));
        return expressions;
    }
}