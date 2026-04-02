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
        return lib.expression.ExpressionFactory.conditional(
            lib.expression.ExpressionFactory.conjunction(
                lib.expression.ExpressionFactory.lessThan(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("10")),
                lib.expression.ExpressionFactory.logicalNot(lib.expression.ExpressionFactory.equality(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("0")))
            ),
            lib.expression.ExpressionFactory.addition(
                lib.expression.ExpressionFactory.subtraction(lib.expression.ExpressionFactory.literal("7"), lib.expression.ExpressionFactory.literal("2")),
                lib.expression.ExpressionFactory.multiplication(
                    lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.literal("2")),
                    lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("9"), lib.expression.ExpressionFactory.literal("4"))
                )
            ),
            lib.expression.ExpressionFactory.functionCall(
                lib.expression.ExpressionFactory.variableReference("f"),
                lib.expression.ExpressionFactory.exponentiation(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3")),
                lib.expression.ExpressionFactory.inequality(lib.expression.ExpressionFactory.literal("5"), lib.expression.ExpressionFactory.literal("6")),
                lib.expression.ExpressionFactory.greaterThan(lib.expression.ExpressionFactory.literal("7"), lib.expression.ExpressionFactory.literal("1")),
                lib.expression.ExpressionFactory.lessThanOrEqual(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("2")),
                lib.expression.ExpressionFactory.greaterThanOrEqual(lib.expression.ExpressionFactory.literal("3"), lib.expression.ExpressionFactory.literal("3")),
                lib.expression.ExpressionFactory.disjunction(lib.expression.ExpressionFactory.literal("0"), lib.expression.ExpressionFactory.literal("1")),
                lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("4"))
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
        expressions.add(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")));
        expressions.add(lib.expression.ExpressionFactory.subtraction(lib.expression.ExpressionFactory.literal("3"), lib.expression.ExpressionFactory.literal("1")));
        expressions.add(lib.expression.ExpressionFactory.multiplication(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3")));
        expressions.add(lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("6"), lib.expression.ExpressionFactory.literal("2")));
        expressions.add(lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("3")));
        expressions.add(lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("7"), lib.expression.ExpressionFactory.literal("3")));
        expressions.add(lib.expression.ExpressionFactory.exponentiation(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3")));
        expressions.add(lib.expression.ExpressionFactory.equality(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("1")));
        expressions.add(lib.expression.ExpressionFactory.inequality(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")));
        expressions.add(lib.expression.ExpressionFactory.lessThan(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")));
        expressions.add(lib.expression.ExpressionFactory.greaterThan(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("1")));
        expressions.add(lib.expression.ExpressionFactory.lessThanOrEqual(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("2")));
        expressions.add(lib.expression.ExpressionFactory.greaterThanOrEqual(lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("2")));
        expressions.add(lib.expression.ExpressionFactory.conjunction(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("1")));
        expressions.add(lib.expression.ExpressionFactory.disjunction(lib.expression.ExpressionFactory.literal("0"), lib.expression.ExpressionFactory.literal("1")));
        expressions.add(lib.expression.ExpressionFactory.logicalNot(lib.expression.ExpressionFactory.literal("0")));
        expressions.add(lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3")));
        expressions.add(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("sum"), lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")));
        return expressions;
    }
}