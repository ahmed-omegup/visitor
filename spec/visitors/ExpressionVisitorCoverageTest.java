package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import ds.Dict;
import java.util.List;
import java.util.stream.Stream;
import lib.expression.Expression;
import lib.expression.ExpressionVisitor;
import lib.visitors.IsomorphicGetter;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

@SuppressWarnings({"rawtypes", "unchecked"})
class ExpressionVisitorCoverageTest {
    private static final ExpressionVisitor<Integer, Object> VISITOR = new IsomorphicGetter<>(new Dict<>(42));

    @TestFactory
    Stream<DynamicTest> allExpressionImplementationsAcceptExpressionVisitor() throws Exception {
        var implementations = ExpressionImplementationFinder.findExpressionImplementations();

        assertFalse(implementations.isEmpty(), "Expected at least one Expression implementation");

        return implementations.stream()
            .map(expressionClass -> DynamicTest.dynamicTest(expressionClass.getSimpleName(), () -> {
                var expression = ReflectiveExpressionInstantiator.instantiateExpression(expressionClass);

                assertEquals(42, accept(expression));
            }));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static int accept(Expression expression) {
        return (Integer) expression.accept((ExpressionVisitor) VISITOR);
    }
}