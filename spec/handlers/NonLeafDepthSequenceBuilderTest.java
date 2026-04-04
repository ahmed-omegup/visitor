package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.NonLeafDepthSequenceBuilder;

abstract class NonLeafDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    NonLeafDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsCompositeNodesWithTheirDepth() {
        assertEquals(
            of("0:Addition", "1:Negation"),
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(testSupport.v.nonLeafDepthSequenceBuilder())
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
            ),
testSupport.sampleTraversalExpression().accept(testSupport.v.nonLeafDepthSequenceBuilder())
        );
    }
}

class NonLeafDepthSequenceBuilderTest extends NonLeafDepthSequenceBuilderTestBase<Expression> {
    NonLeafDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
