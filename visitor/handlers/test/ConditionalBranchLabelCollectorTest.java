package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import visitor.expression.Conditional;
import visitor.expression.Expression;
import visitor.expression.FunctionCall;
import visitor.expression.LessThan;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.ConditionalBranchLabelCollector;

class ConditionalBranchLabelCollectorTest {
    @Test
    void reportsDirectChildKindsForConditionalBranches() {
        assertEquals(
            List.of("condition=LessThan", "whenTrue=Literal", "whenFalse=FunctionCall"),
            new ConditionalBranchLabelCollector().handle(
                new Conditional(
                    new LessThan(new VariableReference("x"), new Literal("1")),
                    new Literal("2"),
                    new FunctionCall(new VariableReference("fallback"), new Literal("0"))
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
        cases.add(new VariableReference("x"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());
        cases.add(new Literal("1"));

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("condition-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    "condition=" + expression.getClass().getSimpleName(),
                    new ConditionalBranchLabelCollector().handle(new Conditional(expression, new Literal("2"), new Literal("3"))).get(0)
                )))
            .toList();
    }
}