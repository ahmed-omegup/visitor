package spec.handlers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.Expression;
import lib.expression.FunctionCall;
import lib.expression.GreaterThan;
import lib.expression.GreaterThanOrEqual;
import lib.expression.Inequality;
import lib.expression.LessThan;
import lib.expression.LessThanOrEqual;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Modulo;
import lib.expression.Multiplication;
import lib.expression.Negation;
import lib.expression.Subtraction;
import lib.expression.VariableReference;

final class TestSupport {
    private TestSupport() {}

    static Expression sampleTraversalExpression() {
        return lib.expression.Expression.conditional(
            lib.expression.Expression.conjunction(
                lib.expression.Expression.lessThan(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("10")),
                lib.expression.Expression.logicalNot(lib.expression.Expression.equality(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("0")))
            ),
            lib.expression.Expression.addition(
                lib.expression.Expression.subtraction(lib.expression.Expression.literal("7"), lib.expression.Expression.literal("2")),
                lib.expression.Expression.multiplication(
                    lib.expression.Expression.division(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2")),
                    lib.expression.Expression.modulo(lib.expression.Expression.literal("9"), lib.expression.Expression.literal("4"))
                )
            ),
            lib.expression.Expression.functionCall(
                lib.expression.Expression.variableReference("f"),
                lib.expression.Expression.exponentiation(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3")),
                lib.expression.Expression.inequality(lib.expression.Expression.literal("5"), lib.expression.Expression.literal("6")),
                lib.expression.Expression.greaterThan(lib.expression.Expression.literal("7"), lib.expression.Expression.literal("1")),
                lib.expression.Expression.lessThanOrEqual(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2")),
                lib.expression.Expression.greaterThanOrEqual(lib.expression.Expression.literal("3"), lib.expression.Expression.literal("3")),
                lib.expression.Expression.disjunction(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("1")),
                lib.expression.Expression.negation(lib.expression.Expression.literal("4"))
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
        expressions.add(lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")));
        expressions.add(lib.expression.Expression.subtraction(lib.expression.Expression.literal("3"), lib.expression.Expression.literal("1")));
        expressions.add(lib.expression.Expression.multiplication(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3")));
        expressions.add(lib.expression.Expression.division(lib.expression.Expression.literal("6"), lib.expression.Expression.literal("2")));
        expressions.add(lib.expression.Expression.negation(lib.expression.Expression.literal("3")));
        expressions.add(lib.expression.Expression.modulo(lib.expression.Expression.literal("7"), lib.expression.Expression.literal("3")));
        expressions.add(lib.expression.Expression.exponentiation(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3")));
        expressions.add(lib.expression.Expression.equality(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("1")));
        expressions.add(lib.expression.Expression.inequality(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")));
        expressions.add(lib.expression.Expression.lessThan(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")));
        expressions.add(lib.expression.Expression.greaterThan(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("1")));
        expressions.add(lib.expression.Expression.lessThanOrEqual(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2")));
        expressions.add(lib.expression.Expression.greaterThanOrEqual(lib.expression.Expression.literal("2"), lib.expression.Expression.literal("2")));
        expressions.add(lib.expression.Expression.conjunction(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("1")));
        expressions.add(lib.expression.Expression.disjunction(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("1")));
        expressions.add(lib.expression.Expression.logicalNot(lib.expression.Expression.literal("0")));
        expressions.add(lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3")));
        expressions.add(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")));
        return expressions;
    }
}