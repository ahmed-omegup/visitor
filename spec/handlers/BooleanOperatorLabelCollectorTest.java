package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.BooleanOperatorLabelCollector;

class BooleanOperatorLabelCollectorTest {
    @Test
    void collectsBooleanOperatorLabelsInPreorder() {
        assertEquals(List.of("Conjunction", "LogicalNot", "Disjunction"), new BooleanOperatorLabelCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}