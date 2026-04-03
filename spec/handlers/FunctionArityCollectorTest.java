package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.FunctionArityCollector;

class FunctionArityCollectorTest {
    @Test
    void collectsFunctionAritiesInTraversalOrder() {
        assertEquals(List.of(7),sampleTraversalExpression().accept(v.functionArityCollector()));
    }
}