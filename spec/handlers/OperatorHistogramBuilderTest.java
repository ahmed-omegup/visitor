package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.handlers.HandlerFactory;
import lib.handlers.OperatorHistogramBuilder;

abstract class OperatorHistogramBuilderTestBase<E> extends TestBase<E> {
    OperatorHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsRepeatedOperators() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("Addition", 2);
        expected.put("Negation", 1);

        assertEquals(
            expected,testSupport.v.operatorHistogramBuilder().apply(factory.addition(
                    factory.addition(factory.literal("1"), factory.literal("2")),
                    factory.negation(factory.literal("3"))
                ))
        );
    }

    @Test
    void countsAllOperatorKindsInTraversalExpression() {
        var histogram =testSupport.v.operatorHistogramBuilder().apply(testSupport.sampleTraversalExpression());

        for (var operator : new String[] {
            "Conditional", "Conjunction", "LessThan", "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
            "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
            "GreaterThanOrEqual", "Disjunction", "Negation"
        }) {
            assertEquals(1, histogram.get(operator));
        }
    }
}

class OperatorHistogramBuilderTest extends OperatorHistogramBuilderTestBase<Expression> {
    OperatorHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
