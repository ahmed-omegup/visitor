package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.handlers.LeafPathCollector;

abstract class LeafPathCollectorTestBase<E> extends TestBase<E> {
    LeafPathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsLeafPathsAcrossFunctionArguments() {
        assertEquals(
            of("root.callee", "root.arguments[0].left", "root.arguments[0].right"),testSupport.v.leafPathCollector().apply(factory.functionCall(factory.variableReference("f"), of( factory.addition(factory.literal("1"), factory.variableReference("x")))))
        );
    }

    @Test
    void collectsTraversalExpressionLeafPaths() {
        assertEquals(
            of(
                "root.condition.left.left",
                "root.condition.left.right",
                "root.condition.right.operand.left",
                "root.condition.right.operand.right",
                "root.whenTrue.left.left",
                "root.whenTrue.left.right",
                "root.whenTrue.right.left.dividend",
                "root.whenTrue.right.left.divisor",
                "root.whenTrue.right.right.left",
                "root.whenTrue.right.right.right",
                "root.whenFalse.callee",
                "root.whenFalse.arguments[0].base",
                "root.whenFalse.arguments[0].exponent",
                "root.whenFalse.arguments[1].left",
                "root.whenFalse.arguments[1].right",
                "root.whenFalse.arguments[2].left",
                "root.whenFalse.arguments[2].right",
                "root.whenFalse.arguments[3].left",
                "root.whenFalse.arguments[3].right",
                "root.whenFalse.arguments[4].left",
                "root.whenFalse.arguments[4].right",
                "root.whenFalse.arguments[5].left",
                "root.whenFalse.arguments[5].right",
                "root.whenFalse.arguments[6].operand"
            ),testSupport.v.leafPathCollector().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class LeafPathCollectorTest extends LeafPathCollectorTestBase<Expression> {
    LeafPathCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
