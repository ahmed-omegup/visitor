package spec.handlers;

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
                lib.expression.ExpressionFactory.conditional(
                    lib.expression.ExpressionFactory.lessThan(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("1")),
                    lib.expression.ExpressionFactory.literal("2"),
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("fallback"), lib.expression.ExpressionFactory.literal("0"))
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
        cases.add(lib.expression.ExpressionFactory.variableReference("x"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());
        cases.add(lib.expression.ExpressionFactory.literal("1"));

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("condition-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    "condition=" + expression.getClass().getSimpleName(),
                    new ConditionalBranchLabelCollector().handle(lib.expression.ExpressionFactory.conditional(expression, lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3"))).get(0)
                )))
            .toList();
    }
}