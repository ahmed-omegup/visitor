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
                lib.expression.ExpressionFactory.addition(
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("sum"), lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("g")), lib.expression.ExpressionFactory.literal("3"))
                )
            )
        );
    }

    @Test
    void recordsTraversalExpressionFunctionArity() {
        assertEquals(List.of(7), new FunctionAritySequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}