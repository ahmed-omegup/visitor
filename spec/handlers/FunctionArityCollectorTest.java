package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.FunctionArityCollector;

class FunctionArityCollectorTest {
    @Test
    void collectsFunctionAritiesInTraversalOrder() {
        assertEquals(List.of(7), new FunctionArityCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}