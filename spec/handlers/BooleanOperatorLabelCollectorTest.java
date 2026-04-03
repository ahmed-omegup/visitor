package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.BooleanOperatorLabelCollector;

class BooleanOperatorLabelCollectorTest {
    @Test
    void collectsBooleanOperatorLabelsInPreorder() {
        assertEquals(List.of("Conjunction", "LogicalNot", "Disjunction"),sampleTraversalExpression().accept(v.booleanOperatorLabelCollector()));
    }
}