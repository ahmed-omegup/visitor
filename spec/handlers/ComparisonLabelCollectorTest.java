package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonLabelCollector;

class ComparisonLabelCollectorTest {
    @Test
    void collectsComparisonLabelsInPreorder() {
        assertEquals(
            List.of("LessThan", "Equality", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual"),
sampleTraversalExpression().accept(v.comparisonLabelCollector())
        );
    }
}