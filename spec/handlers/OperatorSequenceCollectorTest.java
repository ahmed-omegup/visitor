package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.OperatorSequenceCollector;

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
            ),
testSupport.sampleTraversalExpression().accept(testSupport.v.operatorSequenceCollector())
        );
    }
}

class OperatorSequenceCollectorTest extends OperatorSequenceCollectorTestBase<Expression> {
    OperatorSequenceCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
