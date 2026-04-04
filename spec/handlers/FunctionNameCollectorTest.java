package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

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

abstract class FunctionNameCollectorTestBase<E extends Expression> extends TestBase<E> {
    FunctionNameCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsFunctionNamesFromVariableCallees() {
        assertEquals(
            new LinkedHashSet<>(of("sum", "max")),
factory.addition(
                    factory.functionCall(factory.variableReference("sum"), of( factory.literal("1"))),
                    factory.functionCall(factory.variableReference("max"), of( factory.literal("2"), factory.literal("3")))
                ).accept(testSupport.v.functionNameCollector())
        );
    }

    @Test
    void collectsTraversalExpressionFunctionName() {
        assertEquals(new LinkedHashSet<>(of("f")),testSupport.sampleTraversalExpression().accept(testSupport.v.functionNameCollector()));
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
                    new LinkedHashSet<>(of()),
factory.functionCall(callee, of( factory.literal("9"))).accept(collector)
                )))
            .toList();
    }

    @Test
    void collectsNestedFunctionNamesFromFunctionValuedCallee() {
        assertEquals(
            new LinkedHashSet<>(of("sum")),
factory.functionCall(factory.functionCall(factory.variableReference("sum"), of(factory.literal("1"))), of(factory.literal("9"))).accept(testSupport.v.functionNameCollector())
        );
    }
}

class FunctionNameCollectorTest extends FunctionNameCollectorTestBase<Expression> {
    FunctionNameCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
