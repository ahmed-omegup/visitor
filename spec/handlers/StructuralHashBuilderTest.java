package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Multiplication;
import lib.expression.VariableReference;
import lib.visitors.StructuralHashBuilder;
import port.IFactory;

class StructuralHashBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void hashesEqualShapesEquallyAndDifferentShapesDifferently() {
        var builder = TestSupport.handlers().structuralHashBuilder();

        assertEquals(
factory.addition(factory.variableReference("x"), factory.literal("1")).accept(builder),
factory.addition(factory.variableReference("y"), factory.literal("9")).accept(builder)
        );
        assertNotEquals(
factory.addition(factory.variableReference("x"), factory.literal("1")).accept(builder),
factory.multiplication(factory.variableReference("x"), factory.literal("1")).accept(builder)
        );
    }

    @Test
    void isStableForRepeatedComputation() {
        var builder = TestSupport.handlers().structuralHashBuilder();
        var expression = TestSupport.sampleTraversalExpression();

        assertEquals(expression.accept(builder),expression.accept(builder));
    }
}