package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionCallCounter;

class FunctionCallCounterTest {
    @Test
    void countsNestedFunctionCalls() {
        assertEquals(
            2,
            new FunctionCallCounter().handle(
                lib.expression.Expression.addition(
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("left"), lib.expression.Expression.literal("1")),
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("right"), lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionFunctionCall() {
        assertEquals(1, new FunctionCallCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}