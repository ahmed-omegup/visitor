package spec.visitors;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import lib.expression.Expression;

@SuppressWarnings({"rawtypes", "unchecked"})
final class ReflectiveExpressionInstantiator {
    private ReflectiveExpressionInstantiator() {
    }

    static Expression instantiateExpression(Class<? extends Expression> expressionClass) throws ReflectiveOperationException {
        return (Expression) instantiateType(expressionClass);
    }

    private static Object instantiateType(Class<?> type) throws ReflectiveOperationException {
        if (type == String.class) {
            return "x";
        }

        if (type == Object.class || type == Expression.class) {
            return expressionProxy();
        }

        if (type == boolean.class || type == Boolean.class) {
            return false;
        }

        if (type == int.class || type == Integer.class) {
            return 1;
        }

        if (type == long.class || type == Long.class) {
            return 1L;
        }

        if (type == double.class || type == Double.class) {
            return 1.0d;
        }

        if (type == float.class || type == Float.class) {
            return 1.0f;
        }

        if (type == short.class || type == Short.class) {
            return (short) 1;
        }

        if (type == byte.class || type == Byte.class) {
            return (byte) 1;
        }

        if (type == char.class || type == Character.class) {
            return 'x';
        }

        if (type.isArray()) {
            var array = Array.newInstance(type.getComponentType(), 1);
            Array.set(array, 0, instantiateType(type.getComponentType()));
            return array;
        }

        if (Collection.class.isAssignableFrom(type)) {
            return List.of(expressionProxy());
        }

        if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }

        if (type.isInterface()) {
            return proxyFor(type);
        }

        var constructors = Stream.of(type.getDeclaredConstructors())
            .sorted(Comparator.comparingInt(Constructor::getParameterCount))
            .toList();

        ReflectiveOperationException lastFailure = null;
        for (var constructor : constructors) {
            try {
                constructor.setAccessible(true);
                return constructor.newInstance(argumentsFor(constructor));
            } catch (ReflectiveOperationException exception) {
                lastFailure = exception;
            }
        }

        throw new IllegalStateException("Unable to instantiate " + type.getName(), lastFailure);
    }

    private static Object[] argumentsFor(Constructor<?> constructor) throws ReflectiveOperationException {
        var genericTypes = constructor.getGenericParameterTypes();
        var rawTypes = constructor.getParameterTypes();
        var arguments = new Object[rawTypes.length];

        for (int index = 0; index < rawTypes.length; index++) {
            arguments[index] = instantiateArgument(rawTypes[index], genericTypes[index]);
        }

        return arguments;
    }

    private static Object instantiateArgument(Class<?> rawType, Type genericType) throws ReflectiveOperationException {
        if (genericType instanceof TypeVariable<?>) {
            return expressionProxy();
        }

        if (genericType instanceof ParameterizedType parameterizedType
            && parameterizedType.getRawType() instanceof Class<?> rawParameterizedType
            && Collection.class.isAssignableFrom(rawParameterizedType)) {
            return List.of(expressionProxy());
        }

        return instantiateType(rawType);
    }

    private static Object expressionProxy() {
        return Proxy.newProxyInstance(
            ReflectiveExpressionInstantiator.class.getClassLoader(),
            new Class<?>[] { Expression.class },
            expressionInvocationHandler()
        );
    }

    private static Object proxyFor(Class<?> type) {
        return Proxy.newProxyInstance(
            ReflectiveExpressionInstantiator.class.getClassLoader(),
            new Class<?>[] { type },
            (proxy, method, args) -> defaultValue(method.getReturnType())
        );
    }

    private static InvocationHandler expressionInvocationHandler() {
        return (proxy, method, args) -> {
            if (method.getDeclaringClass() == Object.class) {
                return switch (method.getName()) {
                    case "toString" -> "ExpressionProxy";
                    case "hashCode" -> System.identityHashCode(proxy);
                    case "equals" -> proxy == args[0];
                    default -> defaultValue(method.getReturnType());
                };
            }

            if ("accept".equals(method.getName())) {
                return 42;
            }

            return defaultValue(method.getReturnType());
        };
    }

    private static Object defaultValue(Class<?> returnType) {
        if (!returnType.isPrimitive()) {
            return null;
        }

        if (returnType == boolean.class) {
            return false;
        }

        if (returnType == char.class) {
            return '\0';
        }

        if (returnType == long.class) {
            return 0L;
        }

        if (returnType == float.class) {
            return 0.0f;
        }

        if (returnType == double.class) {
            return 0.0d;
        }

        return 0;
    }
}