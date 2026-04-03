package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Addition;
import lib.expression.Expression;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionCallSignatureCollector;
import port.IFactory;

class FunctionCallSignatureCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void recordsSignaturesForDirectAndFunctionValuedCallees() {
        assertEquals(
            List.of("sum/2", "FunctionCall/1", "g/0"),
factory.addition(
                    factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2")),
                    factory.functionCall(factory.functionCall(factory.variableReference("g")), factory.literal("3"))
                ).accept(v.functionCallSignatureCollector())
        );
    }

    @Test
    void recordsTraversalExpressionFunctionSignature() {
        assertEquals(List.of("f/7"),sampleTraversalExpression().accept(v.functionCallSignatureCollector()));
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedCalleeKind() {
        var cases = new ArrayList<Expression>();
        cases.add(factory.literal("7"));
        cases.add(factory.variableReference("name"));
        cases.addAll(sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("callee-" + expression.getClass().getSimpleName(), () -> {
                var signatures =factory.functionCall(expression, factory.literal("9")).accept(v.functionCallSignatureCollector());
                var expectedLabel = expression instanceof VariableReference variableReference
                    ? variableReference.name
                    : expression.getClass().getSimpleName();
                assertEquals(expectedLabel + "/1", signatures.get(0));
            }))
            .toList();
    }
}