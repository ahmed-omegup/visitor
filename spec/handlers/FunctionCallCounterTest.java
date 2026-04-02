package spec.handlers;

import static lib.expression.Factory.*;

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
                addition(
                    functionCall(variableReference("left"), literal("1")),
                    functionCall(variableReference("right"), literal("2"), literal("3"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionFunctionCall() {
        assertEquals(1, new FunctionCallCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}