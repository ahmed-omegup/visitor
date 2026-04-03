package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

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
import lib.visitors.FunctionArgumentRootCollector;
import port.IFactory;

class FunctionArgumentRootCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void collectsTopLevelArgumentKindsForEachFunctionCall() {
        assertEquals(
            List.of("Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"),
sampleTraversalExpression().accept(v.functionArgumentRootCollector())
        );
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedArgumentRootKind() {
        var cases = new ArrayList<Expression>();
        cases.add(factory.literal("7"));
        cases.add(factory.variableReference("x"));
        cases.addAll(sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("argument-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    expression instanceof VariableReference ? "VariableReference" : expression.getClass().getSimpleName(),
factory.functionCall(factory.variableReference("f"), expression).accept(v.functionArgumentRootCollector()).get(0)
                )))
            .toList();
    }
}