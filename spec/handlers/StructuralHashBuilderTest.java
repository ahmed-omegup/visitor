package spec.handlers;

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
            builder.handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("1"))),
            builder.handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("y"), lib.expression.Expression.literal("9")))
        );
        assertNotEquals(
            builder.handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("1"))),
            builder.handle(lib.expression.Expression.multiplication(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("1")))
        );
    }

    @Test
    void isStableForRepeatedComputation() {
        var builder = new StructuralHashBuilder();
        var expression = TestSupport.sampleTraversalExpression();

        assertEquals(builder.handle(expression), builder.handle(expression));
    }
}