package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.StructuralSignatureBuilder;

class StructuralSignatureBuilderTest {
    @Test
    void ignoresLeafValuesAndKeepsOnlyShape() {
        var builder = new StructuralSignatureBuilder();

        assertEquals(
            builder.handle(new Addition(new VariableReference("x"), new Literal("1"))),
            builder.handle(new Addition(new VariableReference("y"), new Literal("9")))
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