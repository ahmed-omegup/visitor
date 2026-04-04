package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.DistinctLeafLabelCollector;
import port.IFactory;

abstract class DistinctLeafLabelCollectorTestBase<E extends Expression> extends TestBase<E> {
    DistinctLeafLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void keepsDistinctLeafLabelsInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(of("literal:1", "variable:f", "variable:x")),
factory.addition(
                    factory.literal("1"),
                    factory.functionCall(factory.variableReference("f"), of( factory.literal("1"), factory.variableReference("x"), factory.variableReference("x")))
                ).accept(testSupport.v.distinctLeafLabelCollector())
        );
    }

    @Test
    void collectsDistinctTraversalExpressionLeafLabels() {
        assertEquals(
            new LinkedHashSet<>(of(
                "variable:x",
                "literal:10",
                "literal:1",
                "literal:0",
                "literal:7",
                "literal:2",
                "literal:8",
                "literal:9",
                "literal:4",
                "variable:f",
                "literal:3",
                "literal:5",
                "literal:6"
            )),
testSupport.sampleTraversalExpression().accept(testSupport.v.distinctLeafLabelCollector())
        );
    }
}

class DistinctLeafLabelCollectorTest extends DistinctLeafLabelCollectorTestBase<Expression> {
    DistinctLeafLabelCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
