package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.LiteralPathCollector;

abstract class LiteralPathCollectorTestBase<E> extends TestBase<E> {
    LiteralPathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void groupsLiteralPathsByValue() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("1", of("root.left", "root.right.arguments[0]"));
        expected.put("2", of("root.right.arguments[1]"));

        assertEquals(
            expected,testSupport.v.literalPathCollector().apply(factory.addition(factory.literal("1"), factory.functionCall(factory.variableReference("f"), of( factory.literal("1"), factory.literal("2")))))
        );
    }

    @Test
    void groupsTraversalExpressionLiteralPaths() {
        var expected = new LinkedHashMap<String, List<String>>();
        expected.put("10", of("root.condition.left.right"));
        expected.put("1", of("root.condition.right.operand.left", "root.whenFalse.arguments[2].right", "root.whenFalse.arguments[5].right"));
        expected.put("0", of("root.condition.right.operand.right", "root.whenFalse.arguments[5].left"));
        expected.put("7", of("root.whenTrue.left.left", "root.whenFalse.arguments[2].left"));
        expected.put("2", of("root.whenTrue.left.right", "root.whenTrue.right.left.divisor", "root.whenFalse.arguments[0].base", "root.whenFalse.arguments[3].left", "root.whenFalse.arguments[3].right"));
        expected.put("8", of("root.whenTrue.right.left.dividend"));
        expected.put("9", of("root.whenTrue.right.right.left"));
        expected.put("4", of("root.whenTrue.right.right.right", "root.whenFalse.arguments[6].operand"));
        expected.put("3", of("root.whenFalse.arguments[0].exponent", "root.whenFalse.arguments[4].left", "root.whenFalse.arguments[4].right"));
        expected.put("5", of("root.whenFalse.arguments[1].left"));
        expected.put("6", of("root.whenFalse.arguments[1].right"));

        assertEquals(expected,testSupport.v.literalPathCollector().apply(testSupport.sampleTraversalExpression()));
    }
}

class LiteralPathCollectorTest extends LiteralPathCollectorTestBase<Expression> {
    LiteralPathCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
