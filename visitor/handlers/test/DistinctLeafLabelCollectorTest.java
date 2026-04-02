package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.DistinctLeafLabelCollector;

class DistinctLeafLabelCollectorTest {
    @Test
    void keepsDistinctLeafLabelsInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(List.of("literal:1", "variable:f", "variable:x")),
            new DistinctLeafLabelCollector().handle(
                new Addition(
                    new Literal("1"),
                    new FunctionCall(new VariableReference("f"), new Literal("1"), new VariableReference("x"), new VariableReference("x"))
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