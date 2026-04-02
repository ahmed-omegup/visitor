package spec.handlers;

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
import lib.handlers.FunctionCallSignatureCollector;

class FunctionCallSignatureCollectorTest {
    @Test
    void recordsSignaturesForDirectAndFunctionValuedCallees() {
        assertEquals(
            List.of("sum/2", "FunctionCall/1", "g/0"),
            new FunctionCallSignatureCollector().handle(
                lib.expression.Expression.addition(
                    lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")),
                    lib.expression.Expression.functionCall(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("g")), lib.expression.Expression.literal("3"))
                )
            )
        );
    }

    @Test
    void recordsTraversalExpressionFunctionSignature() {
        assertEquals(List.of("f/7"), new FunctionCallSignatureCollector().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedCalleeKind() {
        var cases = new ArrayList<Expression>();
        cases.add(lib.expression.Expression.literal("7"));
        cases.add(lib.expression.Expression.variableReference("name"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("callee-" + expression.getClass().getSimpleName(), () -> {
                var signatures = new FunctionCallSignatureCollector().handle(lib.expression.Expression.functionCall(expression, lib.expression.Expression.literal("9")));
                var expectedLabel = expression instanceof VariableReference variableReference
                    ? variableReference.name
                    : expression.getClass().getSimpleName();
                assertEquals(expectedLabel + "/1", signatures.get(0));
            }))
            .toList();
    }
}