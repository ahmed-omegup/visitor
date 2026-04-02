package spec.handlers;

import static lib.expression.Factory.*;

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
                addition(
                    functionCall(variableReference("f"), literal("1")),
                    functionCall(functionCall(variableReference("g")), literal("2"))
                )
            )
        );
    }

    @Test
    void collectsTraversalExpressionFunctionCallPath() {
        assertEquals(List.of("root.whenFalse"), new FunctionCallPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}