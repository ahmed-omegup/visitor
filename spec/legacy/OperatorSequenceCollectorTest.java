package spec.legacy;

import lib.expression.Expression;
import lib.legacy.HandlerFactory;
import lib.legacy.OperatorSequenceCollector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

abstract class OperatorSequenceCollectorTestBase<E> extends TestBase<E> {
    OperatorSequenceCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsOperatorsInPreorder() {
        assertEquals(
            of(
                "Conditional", "Conjunction", "LessThan", "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
                "GreaterThanOrEqual", "Disjunction", "Negation"
            ),testSupport.v.operatorSequenceCollector().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class OperatorSequenceCollectorTest extends OperatorSequenceCollectorTestBase<Expression> {
    OperatorSequenceCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
