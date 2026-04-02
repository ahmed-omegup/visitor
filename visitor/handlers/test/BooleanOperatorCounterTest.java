package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.handlers.BooleanOperatorCounter;

class BooleanOperatorCounterTest {
    @Test
    void countsBooleanOperatorsInTraversalExpression() {
        assertEquals(3, new BooleanOperatorCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}