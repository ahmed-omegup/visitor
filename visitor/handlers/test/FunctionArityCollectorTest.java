package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.handlers.FunctionArityCollector;

class FunctionArityCollectorTest {
    @Test
    void collectsFunctionAritiesInTraversalOrder() {
        assertEquals(List.of(7), new FunctionArityCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}