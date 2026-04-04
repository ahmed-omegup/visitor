package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.VariableReference;
import lib.visitors.VariableUsageCounter;

abstract class VariableUsageCounterTestBase<E extends Expression> extends TestBase<E> {
    VariableUsageCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsVariableUsagesInEncounterOrder() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("x", 2);
        expected.put("sum", 1);

        assertEquals(
            expected,
factory.addition(
                    factory.variableReference("x"),
                    factory.functionCall(factory.variableReference("sum"), of( factory.variableReference("x")))
                ).accept(testSupport.v.variableUsageCounter())
        );
    }

    @Test
    void countsTraversalExpressionVariablesIncludingFunctionCallee() {
        assertEquals(Map.of("x", 1, "f", 1),testSupport.sampleTraversalExpression().accept(testSupport.v.variableUsageCounter()));
    }
}

class VariableUsageCounterTest extends VariableUsageCounterTestBase<Expression> {
    VariableUsageCounterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
