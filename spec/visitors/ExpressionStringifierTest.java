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
        var stringifier = new ExpressionStringifier(testSupport.arithmeticPriorities, (expression, stringify, priority) -> {
            if (expression instanceof Literal literal) {
                return literal.value;
            }
            if (expression instanceof VariableReference variableReference) {
                return variableReference.name;
            }
            if (expression instanceof Addition addition) {
                return ExpressionStringifier.infix(addition.left, "+", addition.right, priority.apply(expression), stringify, priority);
            }
            if (expression instanceof Subtraction subtraction) {
                return ExpressionStringifier.infix(subtraction.left, "-", subtraction.right, priority.apply(expression), stringify, priority);
            }
            if (expression instanceof Multiplication multiplication) {
                return ExpressionStringifier.infix(multiplication.left, "*", multiplication.right, priority.apply(expression), stringify, priority);
            }
            if (expression instanceof Division division) {
                return ExpressionStringifier.infix(division.dividend, "/", division.divisor, priority.apply(expression), stringify, priority);
            }
            if (expression instanceof Negation negation) {
                return ExpressionStringifier.prefix("-", negation.operand, priority.apply(expression), stringify, priority);
            }
            throw new UnsupportedOperationException(expression.getClass().getSimpleName());
        });

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