package spec.handlers;

import static lib.expression.Factory.*;

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
        return conditional(
            conjunction(
                lessThan(variableReference("x"), literal("10")),
                logicalNot(equality(literal("1"), literal("0")))
            ),
            addition(
                subtraction(literal("7"), literal("2")),
                multiplication(
                    division(literal("8"), literal("2")),
                    modulo(literal("9"), literal("4"))
                )
            ),
            functionCall(
                variableReference("f"),
                exponentiation(literal("2"), literal("3")),
                inequality(literal("5"), literal("6")),
                greaterThan(literal("7"), literal("1")),
                lessThanOrEqual(literal("2"), literal("2")),
                greaterThanOrEqual(literal("3"), literal("3")),
                disjunction(literal("0"), literal("1")),
                negation(literal("4"))
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
        expressions.add(addition(literal("1"), literal("2")));
        expressions.add(subtraction(literal("3"), literal("1")));
        expressions.add(multiplication(literal("2"), literal("3")));
        expressions.add(division(literal("6"), literal("2")));
        expressions.add(negation(literal("3")));
        expressions.add(modulo(literal("7"), literal("3")));
        expressions.add(exponentiation(literal("2"), literal("3")));
        expressions.add(equality(literal("1"), literal("1")));
        expressions.add(inequality(literal("1"), literal("2")));
        expressions.add(lessThan(literal("1"), literal("2")));
        expressions.add(greaterThan(literal("2"), literal("1")));
        expressions.add(lessThanOrEqual(literal("2"), literal("2")));
        expressions.add(greaterThanOrEqual(literal("2"), literal("2")));
        expressions.add(conjunction(literal("1"), literal("1")));
        expressions.add(disjunction(literal("0"), literal("1")));
        expressions.add(logicalNot(literal("0")));
        expressions.add(conditional(literal("1"), literal("2"), literal("3")));
        expressions.add(functionCall(variableReference("sum"), literal("1"), literal("2")));
        return expressions;
    }
}