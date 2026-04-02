package spec.handlers;

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
                lib.expression.Expression.addition(
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")),
                    lib.expression.Expression.functionCall(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("g")), lib.expression.Expression.literal("3"))
                )
            )
        );
    }

    @Test
    void recordsTraversalExpressionFunctionArity() {
        assertEquals(List.of(7), new FunctionAritySequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}