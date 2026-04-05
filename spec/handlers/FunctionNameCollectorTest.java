package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

abstract class FunctionNameCollectorTestBase<E> extends TestBase<E> {
    FunctionNameCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsFunctionNamesFromVariableCallees() {
        assertEquals(
            new LinkedHashSet<>(of("sum", "max")),testSupport.v.functionNameCollector().apply(factory.addition(
                    factory.functionCall(factory.variableReference("sum"), of( factory.literal("1"))),
                    factory.functionCall(factory.variableReference("max"), of( factory.literal("2"), factory.literal("3")))
                ))
        );
    }

    @Test
    void collectsTraversalExpressionFunctionName() {
        assertEquals(new LinkedHashSet<>(of("f")),testSupport.v.functionNameCollector().apply(testSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> ignoresNonVariableCalleesAcrossExpressionKinds() {
        var collector = testSupport.v.functionNameCollector();
        var cases = new java.util.ArrayList<E>();
        cases.add(factory.literal("1"));
        cases.addAll(testSupport.sampleNonVariableExpressions().stream()
            .filter(expression -> !typeName(expression).equals("FunctionCall"))
            .toList());
        return cases.stream()
            .map(callee -> DynamicTest.dynamicTest("callee-" + typeName(callee), () ->
                assertEquals(
                    new LinkedHashSet<>(of()),collector.apply(factory.functionCall(callee, of( factory.literal("9"))))
                )))
            .toList();
    }

    @Test
    void collectsNestedFunctionNamesFromFunctionValuedCallee() {
        assertEquals(
            new LinkedHashSet<>(of("sum")),testSupport.v.functionNameCollector().apply(factory.functionCall(factory.functionCall(factory.variableReference("sum"), of(factory.literal("1"))), of(factory.literal("9"))))
        );
    }
}

class FunctionNameCollectorTest extends FunctionNameCollectorTestBase<Expression> {
    FunctionNameCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
