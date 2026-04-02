package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Multiplication;
import visitor.expression.VariableReference;
import visitor.handlers.StructuralHashBuilder;

class StructuralHashBuilderTest {
    @Test
    void hashesEqualShapesEquallyAndDifferentShapesDifferently() {
        var builder = new StructuralHashBuilder();

        assertEquals(
            builder.handle(new Addition(new VariableReference("x"), new Literal("1"))),
            builder.handle(new Addition(new VariableReference("y"), new Literal("9")))
        );
        assertNotEquals(
            builder.handle(new Addition(new VariableReference("x"), new Literal("1"))),
            builder.handle(new Multiplication(new VariableReference("x"), new Literal("1")))
        );
    }

    @Test
    void isStableForRepeatedComputation() {
        var builder = new StructuralHashBuilder();
        var expression = TestSupport.sampleTraversalExpression();

        assertEquals(builder.handle(expression), builder.handle(expression));
    }
}