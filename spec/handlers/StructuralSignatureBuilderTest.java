package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.handlers.StructuralSignatureBuilder;

abstract class StructuralSignatureBuilderTestBase<E> extends TestBase<E> {
    StructuralSignatureBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void ignoresLeafValuesAndKeepsOnlyShape() {
        var builder = testSupport.v.structuralSignatureBuilder();

        assertEquals(builder.apply(factory.addition(factory.variableReference("x"), factory.literal("1"))),builder.apply(factory.addition(factory.variableReference("y"), factory.literal("9")))
        );
    }

    @Test
    void includesAllCompositeTypesInTraversalSignature() {
        var signature =testSupport.v.structuralSignatureBuilder().apply(testSupport.sampleTraversalExpression());

        assertTrue(signature.contains("Conditional("));
        assertTrue(signature.contains("FunctionCall("));
        assertTrue(signature.contains("GreaterThanOrEqual("));
    }
}

class StructuralSignatureBuilderTest extends StructuralSignatureBuilderTestBase<Expression> {
    StructuralSignatureBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
