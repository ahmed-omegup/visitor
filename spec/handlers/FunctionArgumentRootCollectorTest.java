package spec.handlers;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.FunctionArgumentRootCollector;
import port.IFactory;

abstract class FunctionArgumentRootCollectorTestBase<E extends Expression> extends TestBase<E> {
    FunctionArgumentRootCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsTopLevelArgumentKindsForEachFunctionCall() {
        assertEquals(
            List.of("Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"),
testSupport.sampleTraversalExpression().accept(testSupport.v.functionArgumentRootCollector())
        );
    }

    @TestFactory
    Iterable<DynamicTest> labelsEverySupportedArgumentRootKind() {
        var cases = new ArrayList<E>();
        cases.add(factory.literal("7"));
        cases.add(factory.variableReference("x"));
        cases.addAll(testSupport.sampleNonVariableExpressions());

        return cases.stream()
            .map(expression -> DynamicTest.dynamicTest("argument-" + expression.getClass().getSimpleName(), () ->
                assertEquals(
                    expression instanceof VariableReference ? "VariableReference" : expression.getClass().getSimpleName(),
factory.functionCall(factory.variableReference("f"), java.util.List.of( expression)).accept(testSupport.v.functionArgumentRootCollector()).get(0)
                )))
            .toList();
    }
}

class FunctionArgumentRootCollectorTest extends FunctionArgumentRootCollectorTestBase<Expression> {
    FunctionArgumentRootCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
