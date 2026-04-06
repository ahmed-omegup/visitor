package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionCallPathCollector;
import lib.handlers.HandlerFactory;

abstract class FunctionCallPathCollectorTestBase<E> extends TestBase<E> {
    FunctionCallPathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsPathsToNestedFunctionCalls() {
        assertEquals(
            of("root.left", "root.right", "root.right.callee"),testSupport.v.functionCallPathCollector().apply(factory.addition(
                    factory.functionCall(factory.variableReference("f"), of(factory.literal("1"))),
                    factory.functionCall(factory.functionCall(factory.variableReference("g"), of()), of(factory.literal("2")))
                ))
        );
    }

    @Test
    void collectsTraversalExpressionFunctionCallPath() {
        assertEquals(of("root.whenFalse"),testSupport.v.functionCallPathCollector().apply(testSupport.sampleTraversalExpression()));
    }
}

class FunctionCallPathCollectorTest extends FunctionCallPathCollectorTestBase<Expression> {
    FunctionCallPathCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
