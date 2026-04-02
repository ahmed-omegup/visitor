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
                lib.expression.ExpressionFactory.addition(
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("sum"), lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
                    lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("g")), lib.expression.ExpressionFactory.literal("3"))
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
        cases.add(lib.expression.ExpressionFactory.literal("7"));
        cases.add(lib.expression.ExpressionFactory.variableReference("name"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("callee-" + expression.getClass().getSimpleName(), () -> {
                var signatures = new FunctionCallSignatureCollector().handle(lib.expression.ExpressionFactory.functionCall(expression, lib.expression.ExpressionFactory.literal("9")));
                var expectedLabel = expression instanceof VariableReference variableReference
                    ? variableReference.name
                    : expression.getClass().getSimpleName();
                assertEquals(expectedLabel + "/1", signatures.get(0));
            }))
            .toList();
    }
}