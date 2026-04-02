package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.handlers.ComparisonLabelCollector;

class ComparisonLabelCollectorTest {
    @Test
    void collectsComparisonLabelsInPreorder() {
        assertEquals(
            List.of("LessThan", "Equality", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual"),
            new ComparisonLabelCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}