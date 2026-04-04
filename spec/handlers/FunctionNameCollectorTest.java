package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


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

abstract class FunctionNameCollectorTestBase<E extends Expression> extends TestBase<E> {
    FunctionNameCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsFunctionNamesFromVariableCallees() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum", "max")),
factory.addition(
                    factory.functionCall(factory.variableReference("sum"), java.util.List.of( factory.literal("1"))),
                    factory.functionCall(factory.variableReference("max"), java.util.List.of( factory.literal("2"), factory.literal("3")))
                ).accept(testSupport.v.functionNameCollector())
        );
    }

    @Test
    void collectsTraversalExpressionFunctionName() {
        assertEquals(new LinkedHashSet<>(List.of("f")),testSupport.sampleTraversalExpression().accept(testSupport.v.functionNameCollector()));
    }

    @TestFactory
    Iterable<DynamicTest> ignoresNonVariableCalleesAcrossExpressionKinds() {
        var collector = testSupport.v.functionNameCollector();
        var cases = new java.util.ArrayList<E>();
        cases.add(factory.literal("1"));
        cases.addAll(testSupport.sampleNonVariableExpressions().stream()
            .filter(expression -> !(expression instanceof FunctionCall))
            .toList());
        return cases.stream()
            .map(callee -> DynamicTest.dynamicTest("callee-" + callee.getClass().getSimpleName(), () ->
                assertEquals(
                    new LinkedHashSet<>(List.of()),
factory.functionCall(callee, java.util.List.of( factory.literal("9"))).accept(collector)
                )))
            .toList();
    }

    @Test
    void collectsNestedFunctionNamesFromFunctionValuedCallee() {
        assertEquals(
            new LinkedHashSet<>(List.of("sum")),
factory.functionCall(factory.functionCall(factory.variableReference("sum"), java.util.List.of(factory.literal("1"))), java.util.List.of(factory.literal("9"))).accept(testSupport.v.functionNameCollector())
        );
    }
}

class FunctionNameCollectorTest extends FunctionNameCollectorTestBase<Expression> {
    FunctionNameCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
