package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.OperatorSequenceCollector;

class OperatorSequenceCollectorTest {
    @Test
    void collectsOperatorsInPreorder() {
        assertEquals(
            List.of(
                "Conditional", "Conjunction", "LessThan", "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
                "GreaterThanOrEqual", "Disjunction", "Negation"
            ),
            new OperatorSequenceCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}