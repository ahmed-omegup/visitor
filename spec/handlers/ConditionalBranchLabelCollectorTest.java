package spec.handlers;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Conditional;
import lib.expression.Expression;
import lib.visitors.VisitorFactory;
import lib.expression.FunctionCall;
import lib.expression.LessThan;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.ConditionalBranchLabelCollector;

abstract class ConditionalBranchLabelCollectorTestBase<E extends Expression> extends TestBase<E> {
    ConditionalBranchLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void reportsDirectChildKindsForConditionalBranches() {
        assertEquals(
            of("condition=LessThan", "whenTrue=Literal", "whenFalse=FunctionCall"),
factory.conditional(
                    factory.lessThan(factory.variableReference("x"), factory.literal("1")),
                    factory.literal("2"),
                    factory.functionCall(factory.variableReference("fallback"), of( factory.literal("0")))
                ).accept(testSupport.v.conditionalBranchLabelCollector())
        );
    }

    @Test
    void reportsTraversalExpressionBranchKinds() {
        assertEquals(
            of("condition=Conjunction", "whenTrue=Addition", "whenFalse=FunctionCall"),
testSupport.sampleTraversalExpression().accept(testSupport.v.conditionalBranchLabelCollector())
        );
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedConditionalBranchKind() {
        var cases = new ArrayList<E>();
        cases.add(factory.variableReference("x"));
        cases.addAll(testSupport.sampleNonVariableExpressions());
        cases.add(factory.literal("1"));

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("condition-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    "condition=" + expression.getClass().getSimpleName(),
factory.conditional(expression, factory.literal("2"), factory.literal("3")).accept(testSupport.v.conditionalBranchLabelCollector()).get(0)
                )))
            .toList();
    }
}

class ConditionalBranchLabelCollectorTest extends ConditionalBranchLabelCollectorTestBase<Expression> {
    ConditionalBranchLabelCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
