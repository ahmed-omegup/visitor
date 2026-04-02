package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.StructuralSignatureBuilder;

class StructuralSignatureBuilderTest {
    private final Factory factory = new Factory();
    @Test
    void ignoresLeafValuesAndKeepsOnlyShape() {
        var builder = new StructuralSignatureBuilder();

        assertEquals(
            builder.handle(factory.addition(factory.variableReference("x"), factory.literal("1"))),
            builder.handle(factory.addition(factory.variableReference("y"), factory.literal("9")))
        );
    }

    @Test
    void includesAllCompositeTypesInTraversalSignature() {
        var signature = new StructuralSignatureBuilder().handle(TestSupport.sampleTraversalExpression());

        assertTrue(signature.contains("Conditional("));
        assertTrue(signature.contains("FunctionCall("));
        assertTrue(signature.contains("GreaterThanOrEqual("));
    }
}