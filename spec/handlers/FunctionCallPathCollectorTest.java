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
                lib.expression.ExpressionFactory.addition(
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("f"), lib.expression.ExpressionFactory.literal("1")),
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("g")), lib.expression.ExpressionFactory.literal("2"))
                )
            )
        );
    }

    @Test
    void collectsTraversalExpressionFunctionCallPath() {
        assertEquals(List.of("root.whenFalse"), new FunctionCallPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}