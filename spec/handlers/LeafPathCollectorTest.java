package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.LeafPathCollector;
import port.IFactory;

class LeafPathCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void collectsLeafPathsAcrossFunctionArguments() {
        assertEquals(
            List.of("root.callee", "root.arguments[0].left", "root.arguments[0].right"),
factory.functionCall(factory.variableReference("f"), factory.addition(factory.literal("1"), factory.variableReference("x"))).accept(TestSupport.handlers().leafPathCollector())
        );
    }

    @Test
    void collectsTraversalExpressionLeafPaths() {
        assertEquals(
            List.of(
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
            ),
TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().leafPathCollector())
        );
    }
}