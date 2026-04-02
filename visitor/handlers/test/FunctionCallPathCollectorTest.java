package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.FunctionCallPathCollector;

class FunctionCallPathCollectorTest {
    @Test
    void collectsPathsToNestedFunctionCalls() {
        assertEquals(
            List.of("root.left", "root.right", "root.right.callee"),
            new FunctionCallPathCollector().handle(
                new Addition(
                    new FunctionCall(new VariableReference("f"), new Literal("1")),
                    new FunctionCall(new FunctionCall(new VariableReference("g")), new Literal("2"))
                )
            )
        );
    }

    @Test
    void collectsTraversalExpressionFunctionCallPath() {
        assertEquals(List.of("root.whenFalse"), new FunctionCallPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}