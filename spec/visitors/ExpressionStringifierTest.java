package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.visitors.ExpressionStringifier;

class ExpressionStringifierTest {
    private final TestSupport testSupport = new TestSupport();
    private final Factory factory = testSupport.factory;

    @Test
    void stringifierUsesPriorityToControlParentheses() {
        class ArithmeticStringifier extends ExpressionStringifier {
            ArithmeticStringifier() {
                super(testSupport.arithmeticPriorities);
            }

            public String visit(Literal expression) { return expression.value; }
            public String visit(VariableReference expression) { return expression.name; }
            public String visit(Addition expression) { return infix(expression.left, "+", expression.right, priority(expression)); }
            public String visit(Subtraction expression) { return infix(expression.left, "-", expression.right, priority(expression)); }
            public String visit(Multiplication expression) { return infix(expression.left, "*", expression.right, priority(expression)); }
            public String visit(Division expression) { return infix(expression.dividend, "/", expression.divisor, priority(expression)); }
            public String visit(Negation expression) { return prefix("-", expression.operand, priority(expression)); }
        }

        var stringifier = new ArithmeticStringifier();

        assertEquals(
            "1 + 2 * 3",
            stringifier.apply(factory.addition(factory.literal("1"), factory.multiplication(factory.literal("2"), factory.literal("3"))))
        );
        assertEquals(
            "(1 + 2) * 3",
            stringifier.apply(factory.multiplication(factory.addition(factory.literal("1"), factory.literal("2")), factory.literal("3")))
        );
    }
}