package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.StructuralSignatureBuilder;

class StructuralSignatureBuilderTest {
    @Test
    void ignoresLeafValuesAndKeepsOnlyShape() {
        var builder = new StructuralSignatureBuilder();

        assertEquals(
            builder.handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1"))),
            builder.handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("y"), lib.expression.ExpressionFactory.literal("9")))
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