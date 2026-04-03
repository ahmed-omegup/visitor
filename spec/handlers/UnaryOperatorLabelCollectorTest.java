package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.UnaryOperatorLabelCollector;

class UnaryOperatorLabelCollectorTest {
    @Test
    void collectsUnaryOperatorLabelsInPreorder() {
        assertEquals(List.of("LogicalNot", "Negation"),TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().unaryOperatorLabelCollector()));
    }
}