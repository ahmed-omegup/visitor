package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.handlers.BooleanOperatorCounter;

class BooleanOperatorCounterTest {
    @Test
    void countsBooleanOperatorsInTraversalExpression() {
        assertEquals(3,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().booleanOperatorCounter()));
    }
}