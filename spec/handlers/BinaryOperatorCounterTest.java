package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryOperatorCounter;

class BinaryOperatorCounterTest {
    @Test
    void countsBinaryOperatorsInTraversalExpression() {
        assertEquals(14,sampleTraversalExpression().accept(v.binaryOperatorCounter()));
    }
}