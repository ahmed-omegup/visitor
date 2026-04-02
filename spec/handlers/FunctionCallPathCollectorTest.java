package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionCallPathCollector;

class FunctionCallPathCollectorTest {
    @Test
    void collectsPathsToNestedFunctionCalls() {
        assertEquals(
            List.of("root.left", "root.right", "root.right.callee"),
            new FunctionCallPathCollector().handle(
                lib.expression.Expression.addition(
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("f"), lib.expression.Expression.literal("1")),
                    lib.expression.Expression.functionCall(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("g")), lib.expression.Expression.literal("2"))
                )
            )
        );
    }

    @Test
    void collectsTraversalExpressionFunctionCallPath() {
        assertEquals(List.of("root.whenFalse"), new FunctionCallPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}