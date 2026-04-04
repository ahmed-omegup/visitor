package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionAritySequenceBuilder;

abstract class FunctionAritySequenceBuilderTestBase<E> extends TestBase<E> {
    FunctionAritySequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsFunctionAritiesInPreorder() {
        assertEquals(
            of(2, 1, 0),
factory.addition(
                    factory.functionCall(factory.variableReference("sum"), of(factory.literal("1"), factory.literal("2"))),
                    factory.functionCall(factory.functionCall(factory.variableReference("g"), of()), of(factory.literal("3")))
                ).accept(testSupport.v.functionAritySequenceBuilder())
        );
    }

    @Test
    void recordsTraversalExpressionFunctionArity() {
        assertEquals(of(7),testSupport.sampleTraversalExpression().accept(testSupport.v.functionAritySequenceBuilder()));
    }
}

class FunctionAritySequenceBuilderTest extends FunctionAritySequenceBuilderTestBase<Expression> {
    FunctionAritySequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
