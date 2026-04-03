package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.UnaryOperatorLabelCollector;

class UnaryOperatorLabelCollectorTest {
    @Test
    void collectsUnaryOperatorLabelsInPreorder() {
        assertEquals(List.of("LogicalNot", "Negation"),sampleTraversalExpression().accept(v.unaryOperatorLabelCollector()));
    }
}