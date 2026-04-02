package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionAritySequenceBuilder;

class FunctionAritySequenceBuilderTest {
    @Test
    void recordsFunctionAritiesInPreorder() {
        assertEquals(
            List.of(2, 1, 0),
            new FunctionAritySequenceBuilder().handle(
                addition(
                    functionCall(variableReference("sum"), literal("1"), literal("2")),
                    functionCall(functionCall(variableReference("g")), literal("3"))
                )
            )
        );
    }

    @Test
    void recordsTraversalExpressionFunctionArity() {
        assertEquals(List.of(7), new FunctionAritySequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}