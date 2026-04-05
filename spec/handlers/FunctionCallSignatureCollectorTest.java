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

abstract class FunctionCallSignatureCollectorTestBase<E> extends TestBase<E> {
    FunctionCallSignatureCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsSignaturesForDirectAndFunctionValuedCallees() {
        assertEquals(
            of("sum/2", "FunctionCall/1", "g/0"),testSupport.v.functionCallSignatureCollector().apply(factory.addition(
                    factory.functionCall(factory.variableReference("sum"), of(factory.literal("1"), factory.literal("2"))),
                    factory.functionCall(factory.functionCall(factory.variableReference("g"), of()), of(factory.literal("3")))
                ))
        );
    }

    @Test
    void recordsTraversalExpressionFunctionSignature() {
        assertEquals(of("f/7"),testSupport.v.functionCallSignatureCollector().apply(testSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedCalleeKind() {
        var cases = new ArrayList<E>();
        cases.add(factory.literal("7"));
        cases.add(factory.variableReference("name"));
        cases.addAll(testSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("callee-" + typeName(expression), () -> {
                var signatures =testSupport.v.functionCallSignatureCollector().apply(factory.functionCall(expression, of( factory.literal("9"))));
                var expectedLabel = typeName(expression).equals("VariableReference") ? render(expression) : typeName(expression);
                assertEquals(expectedLabel + "/1", signatures.get(0));
            }))
            .toList();
    }
}

class FunctionCallSignatureCollectorTest extends FunctionCallSignatureCollectorTestBase<Expression> {
    FunctionCallSignatureCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
