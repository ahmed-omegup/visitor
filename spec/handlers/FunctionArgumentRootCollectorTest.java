package spec.handlers;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;

abstract class FunctionArgumentRootCollectorTestBase<E> extends TestBase<E> {
    FunctionArgumentRootCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsTopLevelArgumentKindsForEachFunctionCall() {
        assertEquals(
            of("Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"),testSupport.v.functionArgumentRootCollector().apply(testSupport.sampleTraversalExpression())
        );
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedArgumentRootKind() {
        var cases = new ArrayList<E>();
        cases.add(factory.literal("7"));
        cases.add(factory.variableReference("x"));
        cases.addAll(testSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("argument-" + typeName(expression), () ->
                assertEquals(
                    typeName(expression),testSupport.v.functionArgumentRootCollector().apply(factory.functionCall(factory.variableReference("f"), of( expression))).get(0)
                )))
            .toList();
    }
}

class FunctionArgumentRootCollectorTest extends FunctionArgumentRootCollectorTestBase<Expression> {
    FunctionArgumentRootCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
