package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionAritySequenceBuilder;

class FunctionAritySequenceBuilderTest {
    private final Factory factory = new Factory();
    @Test
    void recordsFunctionAritiesInPreorder() {
        assertEquals(
            List.of(2, 1, 0),
            new FunctionAritySequenceBuilder().handle(
                factory.addition(
                    factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2")),
                    factory.functionCall(factory.functionCall(factory.variableReference("g")), factory.literal("3"))
                )
            )
        );
    }

    @Test
    void recordsTraversalExpressionFunctionArity() {
        assertEquals(List.of(7), new FunctionAritySequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}