package spec.handlers;

import lib.expression.Factory;
import lib.visitors.LiteralFrequencyBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

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

        assertEquals(expected,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().literalFrequencyBuilder()));
    }

    @Test
    void returnsEmptyMapWhenNoLiteralsExist() {
        assertEquals(
            new LinkedHashMap<String, Integer>(),
factory.variableReference("x").accept(TestSupport.handlers().literalFrequencyBuilder())
        );
    }
}