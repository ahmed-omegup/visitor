package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Multiplication;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.StructuralHashBuilder;

abstract class StructuralHashBuilderTestBase<E> extends TestBase<E> {
    StructuralHashBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void hashesEqualShapesEquallyAndDifferentShapesDifferently() {
        var builder = testSupport.v.structuralHashBuilder();

        assertEquals(builder.apply(factory.addition(factory.variableReference("x"), factory.literal("1"))),builder.apply(factory.addition(factory.variableReference("y"), factory.literal("9")))
        );
        assertNotEquals(builder.apply(factory.addition(factory.variableReference("x"), factory.literal("1"))),builder.apply(factory.multiplication(factory.variableReference("x"), factory.literal("1")))
        );
    }

    @Test
    void isStableForRepeatedComputation() {
        var builder = testSupport.v.structuralHashBuilder();
        var expression = testSupport.sampleTraversalExpression();

        assertEquals(builder.apply(expression),builder.apply(expression));
    }
}

class StructuralHashBuilderTest extends StructuralHashBuilderTestBase<Expression> {
    StructuralHashBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
