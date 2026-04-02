package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.handlers.LiteralFrequencyBuilder;
import port.IFactory;

class LiteralFrequencyBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void countsLiteralOccurrences() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("10", 1);
        expected.put("1", 3);
        expected.put("0", 2);
        expected.put("7", 2);
        expected.put("2", 5);
        expected.put("8", 1);
        expected.put("9", 1);
        expected.put("4", 2);
        expected.put("3", 3);
        expected.put("5", 1);
        expected.put("6", 1);

        assertEquals(expected, new LiteralFrequencyBuilder().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void returnsEmptyMapWhenNoLiteralsExist() {
        assertEquals(
            new LinkedHashMap<String, Integer>(),
            new LiteralFrequencyBuilder().handle(factory.variableReference("x"))
        );
    }
}