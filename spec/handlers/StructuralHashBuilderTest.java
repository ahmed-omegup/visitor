package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Multiplication;
import lib.expression.VariableReference;
import lib.visitors.StructuralHashBuilder;

abstract class StructuralHashBuilderTestBase<E> extends TestBase<E> {
    StructuralHashBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void hashesEqualShapesEquallyAndDifferentShapesDifferently() {
        var builder = testSupport.v.structuralHashBuilder();

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
        var builder = testSupport.v.structuralHashBuilder();
        var expression = testSupport.sampleTraversalExpression();

        assertEquals(expression.accept(builder),expression.accept(builder));
    }
}

class StructuralHashBuilderTest extends StructuralHashBuilderTestBase<Expression> {
    StructuralHashBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
