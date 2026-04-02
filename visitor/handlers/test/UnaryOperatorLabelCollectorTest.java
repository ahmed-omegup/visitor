package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.handlers.UnaryOperatorLabelCollector;

class UnaryOperatorLabelCollectorTest {
    @Test
    void collectsUnaryOperatorLabelsInPreorder() {
        assertEquals(List.of("LogicalNot", "Negation"), new UnaryOperatorLabelCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}