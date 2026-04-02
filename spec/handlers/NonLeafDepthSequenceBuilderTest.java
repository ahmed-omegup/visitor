package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.NonLeafDepthSequenceBuilder;

class NonLeafDepthSequenceBuilderTest {
    @Test
    void recordsCompositeNodesWithTheirDepth() {
        assertEquals(
            List.of("0:Addition", "1:Negation"),
            new NonLeafDepthSequenceBuilder().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.negation(lib.expression.Expression.literal("2"))))
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