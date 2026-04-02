package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.handlers.BinaryOperatorCounter;

class BinaryOperatorCounterTest {
    @Test
    void countsBinaryOperatorsInTraversalExpression() {
        assertEquals(14, new BinaryOperatorCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}