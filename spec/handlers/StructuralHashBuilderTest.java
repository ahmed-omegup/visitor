package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Multiplication;
import lib.expression.VariableReference;
import lib.handlers.StructuralHashBuilder;
import port.IFactory;

class StructuralHashBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void hashesEqualShapesEquallyAndDifferentShapesDifferently() {
        var builder = new StructuralHashBuilder();

        assertEquals(
            builder.handle(factory.addition(factory.variableReference("x"), factory.literal("1"))),
            builder.handle(factory.addition(factory.variableReference("y"), factory.literal("9")))
        );
        assertNotEquals(
            builder.handle(factory.addition(factory.variableReference("x"), factory.literal("1"))),
            builder.handle(factory.multiplication(factory.variableReference("x"), factory.literal("1")))
        );
    }

    @Test
    void isStableForRepeatedComputation() {
        var builder = new StructuralHashBuilder();
        var expression = TestSupport.sampleTraversalExpression();

        assertEquals(builder.handle(expression), builder.handle(expression));
    }
}