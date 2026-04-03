package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionCallPathCollector;
import port.IFactory;

class FunctionCallPathCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void collectsPathsToNestedFunctionCalls() {
        assertEquals(
            List.of("root.left", "root.right", "root.right.callee"),
factory.addition(
                    factory.functionCall(factory.variableReference("f"), factory.literal("1")),
                    factory.functionCall(factory.functionCall(factory.variableReference("g")), factory.literal("2"))
                ).accept(TestSupport.handlers().functionCallPathCollector())
        );
    }

    @Test
    void collectsTraversalExpressionFunctionCallPath() {
        assertEquals(List.of("root.whenFalse"),TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().functionCallPathCollector()));
    }
}