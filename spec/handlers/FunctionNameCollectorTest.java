package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionNameCollector;
import port.IFactory;

class FunctionNameCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void collectsFunctionNamesFromVariableCallees() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum", "max")),
factory.addition(
                    factory.functionCall(factory.variableReference("sum"), factory.literal("1")),
                    factory.functionCall(factory.variableReference("max"), factory.literal("2"), factory.literal("3"))
                ).accept(TestSupport.handlers().functionNameCollector())
        );
    }

    @Test
    void collectsTraversalExpressionFunctionName() {
        assertEquals(new LinkedHashSet<>(List.of("f")),TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().functionNameCollector()));
    }

    @TestFactory
    Iterable<DynamicTest> ignoresNonVariableCalleesAcrossExpressionKinds() {
        var collector = TestSupport.handlers().functionNameCollector();
        var cases = new java.util.ArrayList<lib.expression.Expression>();
        cases.add(factory.literal("1"));
        cases.addAll(TestSupport.sampleNonVariableExpressions().stream()
            .filter(expression -> !(expression instanceof FunctionCall))
            .toList());
        return cases.stream()
            .map(callee -> DynamicTest.dynamicTest("callee-" + callee.getClass().getSimpleName(), () ->
                assertEquals(
                    new LinkedHashSet<>(List.of()),
factory.functionCall(callee, factory.literal("9")).accept(collector)
                )))
            .toList();
    }

    @Test
    void collectsNestedFunctionNamesFromFunctionValuedCallee() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum")),
factory.functionCall(factory.functionCall(factory.variableReference("sum"), factory.literal("1")), factory.literal("9")).accept(TestSupport.handlers().functionNameCollector())
        );
    }
}