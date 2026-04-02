package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.FunctionAritySequenceBuilder;

class FunctionAritySequenceBuilderTest {
    @Test
    void recordsFunctionAritiesInPreorder() {
        assertEquals(
            List.of(2, 1, 0),
            new FunctionAritySequenceBuilder().handle(
                new Addition(
                    new FunctionCall(new VariableReference("sum"), new Literal("1"), new Literal("2")),
                    new FunctionCall(new FunctionCall(new VariableReference("g")), new Literal("3"))
                )
            )
        );
    }

    @Test
    void recordsTraversalExpressionFunctionArity() {
        assertEquals(List.of(7), new FunctionAritySequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}