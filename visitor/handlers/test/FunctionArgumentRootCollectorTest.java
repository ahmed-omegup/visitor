package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import visitor.expression.Expression;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.FunctionArgumentRootCollector;

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
        cases.add(new Literal("7"));
        cases.add(new VariableReference("x"));
        cases.addAll(TestSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("argument-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    expression instanceof VariableReference ? "VariableReference" : expression.getClass().getSimpleName(),
                    new FunctionArgumentRootCollector().handle(new FunctionCall(new VariableReference("f"), expression)).get(0)
                )))
            .toList();
    }
}