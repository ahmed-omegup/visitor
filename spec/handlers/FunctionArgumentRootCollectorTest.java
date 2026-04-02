package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Expression;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.FunctionArgumentRootCollector;

class FunctionArgumentRootCollectorTest {
    @Test
    void collectsTopLevelArgumentKindsForEachFunctionCall() {
        assertEquals(
            List.of("Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"),
            new FunctionArgumentRootCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedArgumentRootKind() {
        var cases = new ArrayList<Expression>();
        cases.add(literal("7"));
        cases.add(variableReference("x"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("argument-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    expression instanceof VariableReference ? "VariableReference" : expression.getClass().getSimpleName(),
                    new FunctionArgumentRootCollector().handle(functionCall(variableReference("f"), expression)).get(0)
                )))
            .toList();
    }
}