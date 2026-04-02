package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import visitor.expression.Addition;
import visitor.expression.Expression;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.FunctionCallSignatureCollector;

class FunctionCallSignatureCollectorTest {
    @Test
    void recordsSignaturesForDirectAndFunctionValuedCallees() {
        assertEquals(
            List.of("sum/2", "FunctionCall/1", "g/0"),
            new FunctionCallSignatureCollector().handle(
                new Addition(
                    new FunctionCall(new VariableReference("sum"), new Literal("1"), new Literal("2")),
                    new FunctionCall(new FunctionCall(new VariableReference("g")), new Literal("3"))
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
        cases.add(new Literal("7"));
        cases.add(new VariableReference("name"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("callee-" + expression.getClass().getSimpleName(), () -> {
                var signatures = new FunctionCallSignatureCollector().handle(new FunctionCall(expression, new Literal("9")));
                var expectedLabel = expression instanceof VariableReference variableReference
                    ? variableReference.name
                    : expression.getClass().getSimpleName();
                assertEquals(expectedLabel + "/1", signatures.get(0));
            }))
            .toList();
    }
}