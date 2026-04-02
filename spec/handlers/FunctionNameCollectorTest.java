package spec.handlers;

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
    @Test
    void collectsFunctionNamesFromVariableCallees() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum", "max")),
            new FunctionNameCollector().handle(
                lib.expression.Expression.addition(
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1")),
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("max"), lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3"))
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
        cases.add(lib.expression.Expression.literal("1"));
        cases.addAll(TestSupport.sampleNonVariableExpressions().stream()
            .filter(expression -> !(expression instanceof FunctionCall))
            .toList());
        return cases.stream()
            .map(callee -> DynamicTest.dynamicTest("callee-" + callee.getClass().getSimpleName(), () ->
                assertEquals(
                    new LinkedHashSet<>(List.of()),
                    collector.handle(lib.expression.Expression.functionCall(callee, lib.expression.Expression.literal("9")))
                )))
            .toList();
    }

    @Test
    void collectsNestedFunctionNamesFromFunctionValuedCallee() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum")),
            new FunctionNameCollector().handle(
                lib.expression.Expression.functionCall(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1")), lib.expression.Expression.literal("9"))
            )
        );
    }
}