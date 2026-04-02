package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Conditional;
import lib.expression.Expression;
import lib.expression.FunctionCall;
import lib.expression.LessThan;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.ConditionalBranchLabelCollector;

class ConditionalBranchLabelCollectorTest {
    @Test
    void reportsDirectChildKindsForConditionalBranches() {
        assertEquals(
            List.of("condition=LessThan", "whenTrue=Literal", "whenFalse=FunctionCall"),
            new ConditionalBranchLabelCollector().handle(
                conditional(
                    lessThan(variableReference("x"), literal("1")),
                    literal("2"),
                    functionCall(variableReference("fallback"), literal("0"))
                )
            )
        );
    }

    @Test
    void reportsTraversalExpressionBranchKinds() {
        assertEquals(
            List.of("condition=Conjunction", "whenTrue=Addition", "whenFalse=FunctionCall"),
            new ConditionalBranchLabelCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedConditionalBranchKind() {
        var cases = new ArrayList<Expression>();
        cases.add(variableReference("x"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());
        cases.add(literal("1"));

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("condition-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    "condition=" + expression.getClass().getSimpleName(),
                    new ConditionalBranchLabelCollector().handle(conditional(expression, literal("2"), literal("3"))).get(0)
                )))
            .toList();
    }
}