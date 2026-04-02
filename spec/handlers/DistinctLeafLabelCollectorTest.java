package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.DistinctLeafLabelCollector;

class DistinctLeafLabelCollectorTest {
    @Test
    void keepsDistinctLeafLabelsInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(List.of("literal:1", "variable:f", "variable:x")),
            new DistinctLeafLabelCollector().handle(
                addition(
                    literal("1"),
                    functionCall(variableReference("f"), literal("1"), variableReference("x"), variableReference("x"))
                )
            )
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
            new DistinctLeafLabelCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}