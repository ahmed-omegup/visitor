package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.BooleanOperatorCounter;

class BooleanOperatorCounterTest {
    @Test
    void countsBooleanOperatorsInTraversalExpression() {
        assertEquals(3,sampleTraversalExpression().accept(v.booleanOperatorCounter()));
    }
}