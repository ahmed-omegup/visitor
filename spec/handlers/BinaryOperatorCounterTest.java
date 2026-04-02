package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.handlers.BinaryOperatorCounter;

class BinaryOperatorCounterTest {
    @Test
    void countsBinaryOperatorsInTraversalExpression() {
        assertEquals(14, new BinaryOperatorCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}