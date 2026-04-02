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
                lib.expression.ExpressionFactory.addition(
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("left"), lib.expression.ExpressionFactory.literal("1")),
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("right"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionFunctionCall() {
        assertEquals(1, new FunctionCallCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}