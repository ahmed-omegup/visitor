package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.StructuralSignatureBuilder;
import port.IFactory;

class StructuralSignatureBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void ignoresLeafValuesAndKeepsOnlyShape() {
        var builder = TestSupport.handlers().structuralSignatureBuilder();

        assertEquals(
factory.addition(factory.variableReference("x"), factory.literal("1")).accept(builder),
factory.addition(factory.variableReference("y"), factory.literal("9")).accept(builder)
        );
    }

    @Test
    void includesAllCompositeTypesInTraversalSignature() {
        var signature =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().structuralSignatureBuilder());

        assertTrue(signature.contains("Conditional("));
        assertTrue(signature.contains("FunctionCall("));
        assertTrue(signature.contains("GreaterThanOrEqual("));
    }
}