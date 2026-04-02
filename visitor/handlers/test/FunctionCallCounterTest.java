package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.FunctionCallCounter;

class FunctionCallCounterTest {
    @Test
    void countsNestedFunctionCalls() {
        assertEquals(
            2,
            new FunctionCallCounter().handle(
                new Addition(
                    new FunctionCall(new VariableReference("left"), new Literal("1")),
                    new FunctionCall(new VariableReference("right"), new Literal("2"), new Literal("3"))
                )
            )
        );
    }

    @Test
    void countsTraversalExpressionFunctionCall() {
        assertEquals(1, new FunctionCallCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}