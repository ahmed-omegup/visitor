package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.VariableCollector;

abstract class VariableCollectorTestBase<E extends Expression> extends TestBase<E> {
    VariableCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsVariablesInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(of("x", "f")),
testSupport.sampleTraversalExpression().accept(testSupport.v.variableCollector())
        );
    }
}

class VariableCollectorTest extends VariableCollectorTestBase<Expression> {
    VariableCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
