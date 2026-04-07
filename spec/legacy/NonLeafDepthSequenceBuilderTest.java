package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.NonLeafDepthSequenceBuilder;

abstract class NonLeafDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    NonLeafDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsCompositeNodesWithTheirDepth() {
        assertEquals(
            of("0:Addition", "1:Negation"),testSupport.v.nonLeafDepthSequenceBuilder().apply(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void recordsTraversalExpressionCompositeSequence() {
        assertEquals(
            of(
                "0:Conditional",
                "1:Conjunction",
                "2:LessThan",
                "2:LogicalNot",
                "3:Equality",
                "1:Addition",
                "2:Subtraction",
                "2:Multiplication",
                "3:Division",
                "3:Modulo",
                "1:FunctionCall",
                "2:Exponentiation",
                "2:Inequality",
                "2:GreaterThan",
                "2:LessThanOrEqual",
                "2:GreaterThanOrEqual",
                "2:Disjunction",
                "2:Negation"
            ),testSupport.v.nonLeafDepthSequenceBuilder().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class NonLeafDepthSequenceBuilderTest extends NonLeafDepthSequenceBuilderTestBase<Expression> {
    NonLeafDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
