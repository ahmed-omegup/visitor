package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.NonLeafDepthSequenceBuilder;

class NonLeafDepthSequenceBuilderTest {
    @Test
    void recordsCompositeNodesWithTheirDepth() {
        assertEquals(
            List.of("0:Addition", "1:Negation"),
            new NonLeafDepthSequenceBuilder().handle(new Addition(new VariableReference("x"), new Negation(new Literal("2"))))
        );
    }

    @Test
    void recordsTraversalExpressionCompositeSequence() {
        assertEquals(
            List.of(
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
            new NonLeafDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression())
        );
    }
}