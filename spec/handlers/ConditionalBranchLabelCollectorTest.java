package spec.handlers;

import lib.expression.Factory;

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
import port.IFactory;

class ConditionalBranchLabelCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void reportsDirectChildKindsForConditionalBranches() {
        assertEquals(
            List.of("condition=LessThan", "whenTrue=Literal", "whenFalse=FunctionCall"),
factory.conditional(
                    factory.lessThan(factory.variableReference("x"), factory.literal("1")),
                    factory.literal("2"),
                    factory.functionCall(factory.variableReference("fallback"), factory.literal("0"))
                ).accept(TestSupport.handlers().conditionalBranchLabelCollector())
        );
    }

    @Test
    void reportsTraversalExpressionBranchKinds() {
        assertEquals(
            List.of("condition=Conjunction", "whenTrue=Addition", "whenFalse=FunctionCall"),
TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().conditionalBranchLabelCollector())
        );
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedConditionalBranchKind() {
        var cases = new ArrayList<Expression>();
        cases.add(factory.variableReference("x"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());
        cases.add(factory.literal("1"));

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("condition-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    "condition=" + expression.getClass().getSimpleName(),
factory.conditional(expression, factory.literal("2"), factory.literal("3")).accept(TestSupport.handlers().conditionalBranchLabelCollector()).get(0)
                )))
            .toList();
    }
}