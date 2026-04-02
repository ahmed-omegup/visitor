package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.handlers.BooleanOperatorLabelCollector;

class BooleanOperatorLabelCollectorTest {
    @Test
    void collectsBooleanOperatorLabelsInPreorder() {
        assertEquals(List.of("Conjunction", "LogicalNot", "Disjunction"), new BooleanOperatorLabelCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}