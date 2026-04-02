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
import lib.handlers.FunctionNameCollector;

class FunctionNameCollectorTest {
    private final Factory factory = new Factory();
    @Test
    void collectsFunctionNamesFromVariableCallees() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum", "max")),
            new FunctionNameCollector().handle(
                factory.addition(
                    factory.functionCall(factory.variableReference("sum"), factory.literal("1")),
                    factory.functionCall(factory.variableReference("max"), factory.literal("2"), factory.literal("3"))
                )
            )
        );
    }

    @Test
    void collectsTraversalExpressionFunctionName() {
        assertEquals(new LinkedHashSet<>(List.of("f")), new FunctionNameCollector().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> ignoresNonVariableCalleesAcrossExpressionKinds() {
        var collector = new FunctionNameCollector();
        var cases = new java.util.ArrayList<lib.expression.Expression>();
        cases.add(factory.literal("1"));
        cases.addAll(TestSupport.sampleNonVariableExpressions().stream()
            .filter(expression -> !(expression instanceof FunctionCall))
            .toList());
        return cases.stream()
            .map(callee -> DynamicTest.dynamicTest("callee-" + callee.getClass().getSimpleName(), () ->
                assertEquals(
                    new LinkedHashSet<>(List.of()),
                    collector.handle(factory.functionCall(callee, factory.literal("9")))
                )))
            .toList();
    }

    @Test
    void collectsNestedFunctionNamesFromFunctionValuedCallee() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum")),
            new FunctionNameCollector().handle(
                factory.functionCall(factory.functionCall(factory.variableReference("sum"), factory.literal("1")), factory.literal("9"))
            )
        );
    }
}