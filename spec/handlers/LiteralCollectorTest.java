package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.LiteralCollector;

class LiteralCollectorTest {
    @Test
    void collectsLiteralsInTraversalOrder() {
        assertEquals(
            List.of("10", "1", "0", "7", "2", "8", "2", "9", "4", "2", "3", "5", "6", "7", "1", "2", "2", "3", "3", "0", "1", "4"),
            new LiteralCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}