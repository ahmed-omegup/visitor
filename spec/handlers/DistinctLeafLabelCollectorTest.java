package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.DistinctLeafLabelCollector;
import port.IFactory;

class DistinctLeafLabelCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void keepsDistinctLeafLabelsInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(List.of("literal:1", "variable:f", "variable:x")),
factory.addition(
                    factory.literal("1"),
                    factory.functionCall(factory.variableReference("f"), factory.literal("1"), factory.variableReference("x"), factory.variableReference("x"))
                ).accept(TestSupport.handlers().distinctLeafLabelCollector())
        );
    }

    @Test
    void collectsDistinctTraversalExpressionLeafLabels() {
        assertEquals(
            new LinkedHashSet<>(List.of(
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
TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().distinctLeafLabelCollector())
        );
    }
}