package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Multiplication;
import lib.expression.VariableReference;
import lib.handlers.StructuralHashBuilder;

class StructuralHashBuilderTest {
    @Test
    void hashesEqualShapesEquallyAndDifferentShapesDifferently() {
        var builder = new StructuralHashBuilder();

        assertEquals(
            builder.handle(addition(variableReference("x"), literal("1"))),
            builder.handle(addition(variableReference("y"), literal("9")))
        );
        assertNotEquals(
            builder.handle(addition(variableReference("x"), literal("1"))),
            builder.handle(multiplication(variableReference("x"), literal("1")))
        );
    }

    @Test
    void isStableForRepeatedComputation() {
        var builder = new StructuralHashBuilder();
        var expression = TestSupport.sampleTraversalExpression();

        assertEquals(builder.handle(expression), builder.handle(expression));
    }
}