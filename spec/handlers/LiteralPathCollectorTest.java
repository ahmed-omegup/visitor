package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.LiteralPathCollector;
import port.IFactory;

class LiteralPathCollectorTest {
    private final IFactory factory = new Factory();
    @Test
    void groupsLiteralPathsByValue() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("1", List.of("root.left", "root.right.arguments[0]"));
        expected.put("2", List.of("root.right.arguments[1]"));

        assertEquals(
            expected,
factory.addition(factory.literal("1"), factory.functionCall(factory.variableReference("f"), factory.literal("1"), factory.literal("2"))).accept(v.literalPathCollector())
        );
    }

    @Test
    void groupsTraversalExpressionLiteralPaths() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("10", List.of("root.condition.left.right"));
        expected.put("1", List.of("root.condition.right.operand.left", "root.whenFalse.arguments[2].right", "root.whenFalse.arguments[5].right"));
        expected.put("0", List.of("root.condition.right.operand.right", "root.whenFalse.arguments[5].left"));
        expected.put("7", List.of("root.whenTrue.left.left", "root.whenFalse.arguments[2].left"));
        expected.put("2", List.of("root.whenTrue.left.right", "root.whenTrue.right.left.divisor", "root.whenFalse.arguments[0].base", "root.whenFalse.arguments[3].left", "root.whenFalse.arguments[3].right"));
        expected.put("8", List.of("root.whenTrue.right.left.dividend"));
        expected.put("9", List.of("root.whenTrue.right.right.left"));
        expected.put("4", List.of("root.whenTrue.right.right.right", "root.whenFalse.arguments[6].operand"));
        expected.put("3", List.of("root.whenFalse.arguments[0].exponent", "root.whenFalse.arguments[4].left", "root.whenFalse.arguments[4].right"));
        expected.put("5", List.of("root.whenFalse.arguments[1].left"));
        expected.put("6", List.of("root.whenFalse.arguments[1].right"));

        assertEquals(expected,sampleTraversalExpression().accept(v.literalPathCollector()));
    }
}