package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.FunctionNameCollector;

class FunctionNameCollectorTest {
    @Test
    void collectsFunctionNamesFromVariableCallees() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum", "max")),
            new FunctionNameCollector().handle(
                new Addition(
                    new FunctionCall(new VariableReference("sum"), new Literal("1")),
                    new FunctionCall(new VariableReference("max"), new Literal("2"), new Literal("3"))
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
        var cases = new java.util.ArrayList<visitor.expression.Expression>();
        cases.add(new Literal("1"));
        cases.addAll(TestSupport.sampleNonVariableExpressions().stream()
            .filter(expression -> !(expression instanceof FunctionCall))
            .toList());
        return cases.stream()
            .map(callee -> DynamicTest.dynamicTest("callee-" + callee.getClass().getSimpleName(), () ->
                assertEquals(
                    new LinkedHashSet<>(List.of()),
                    collector.handle(new FunctionCall(callee, new Literal("9")))
                )))
            .toList();
    }

    @Test
    void collectsNestedFunctionNamesFromFunctionValuedCallee() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum")),
            new FunctionNameCollector().handle(
                new FunctionCall(new FunctionCall(new VariableReference("sum"), new Literal("1")), new Literal("9"))
            )
        );
    }
}