package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.visitors.VariablePathCollector;

abstract class VariablePathCollectorTestBase<E> extends TestBase<E> {
    VariablePathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void groupsVariablePathsByName() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("x", of("root.left", "root.right.arguments[0]"));
        expected.put("f", of("root.right.callee"));

        assertEquals(
            expected,
factory.addition(factory.variableReference("x"), factory.functionCall(factory.variableReference("f"), of( factory.variableReference("x")))).accept(testSupport.v.variablePathCollector())
        );
    }

    @Test
    void groupsTraversalExpressionVariablePaths() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("x", of("root.condition.left.left"));
        expected.put("f", of("root.whenFalse.callee"));

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.variablePathCollector()));
    }
}

class VariablePathCollectorTest extends VariablePathCollectorTestBase<Expression> {
    VariablePathCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
