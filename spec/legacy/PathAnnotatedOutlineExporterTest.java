package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.PathAnnotatedOutlineExporter;

abstract class PathAnnotatedOutlineExporterTestBase<E> extends TestBase<E> {
    PathAnnotatedOutlineExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void annotatesEachNodeWithItsTraversalPath() {
        assertEquals(
            "0 Addition\n"
                + "0.0 VariableReference(x)\n"
                + "0.1 Negation\n"
                + "0.1.0 Literal(2)\n",testSupport.v.pathAnnotatedOutlineExporter().apply(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void coversConditionalAndFunctionCallPathBranches() {
        var outline =testSupport.v.pathAnnotatedOutlineExporter().apply(testSupport.sampleTraversalExpression());

        assertTrue(outline.contains("0 Conditional\n"));
        assertTrue(outline.contains("0.2 FunctionCall\n"));
        assertTrue(outline.contains("0.2.7 Negation\n"));
    }
}

class PathAnnotatedOutlineExporterTest extends PathAnnotatedOutlineExporterTestBase<Expression> {
    PathAnnotatedOutlineExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
