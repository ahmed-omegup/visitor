package spec.handlers;

import lib.expression.Expression;
import lib.handlers.HandlerFactory;
import lib.handlers.VariableCollector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

abstract class VariableCollectorTestBase<E> extends TestBase<E> {
    VariableCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsVariablesInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(of("x", "f")),testSupport.v.variableCollector().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class VariableCollectorTest extends VariableCollectorTestBase<Expression> {
    VariableCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
