package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.NodeTypeCollector;

abstract class NodeTypeCollectorTestBase<E> extends TestBase<E> {
    NodeTypeCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsUniqueTypesInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(of(
                "Conditional", "Conjunction", "LessThan", "VariableReference", "Literal",
                "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality",
                "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"
            )),
testSupport.sampleTraversalExpression().accept(testSupport.v.nodeTypeCollector())
        );
    }
}

class NodeTypeCollectorTest extends NodeTypeCollectorTestBase<Expression> {
    NodeTypeCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
