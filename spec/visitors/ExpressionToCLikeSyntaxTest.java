package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.visitors.ExpressionToCLikeSyntax;

class ExpressionToCLikeSyntaxTest {
    private final TestSupport testSupport = new TestSupport();
    private final Factory factory = testSupport.factory;

    @Test
    void cLikeSyntaxUsesPriorityToControlParentheses() {
        var stringifier = new ExpressionToCLikeSyntax(testSupport.arithmeticPriorities);

        assertEquals(
            "1 + 2 * 3",
            stringifier.apply(factory.addition(factory.literal("1"), factory.multiplication(factory.literal("2"), factory.literal("3"))))
        );
        assertEquals(
            "(1 + 2) * 3",
            stringifier.apply(factory.multiplication(factory.addition(factory.literal("1"), factory.literal("2")), factory.literal("3")))
        );
        assertEquals(
            "10 - (3 - 1)",
            stringifier.apply(factory.subtraction(factory.literal("10"), factory.subtraction(factory.literal("3"), factory.literal("1"))))
        );
    }

    @Test
    void cLikeSyntaxPrintsOperatorsAndCalls() {
        var stringifier = new ExpressionToCLikeSyntax(testSupport.arithmeticPriorities);

        assertEquals(
            "x <= 10 ? f(1, y) : !ready || pow(2, 3)",
            stringifier.apply(factory.conditional(
                factory.lessThanOrEqual(factory.variableReference("x"), factory.literal("10")),
                factory.functionCall(factory.variableReference("f"), java.util.List.of(factory.literal("1"), factory.variableReference("y"))),
                factory.disjunction(factory.logicalNot(factory.variableReference("ready")), factory.exponentiation(factory.literal("2"), factory.literal("3")))
            ))
        );
    }

    @Test
    void cLikeSyntaxUsesRightAssociativeBindingWhenNeeded() {
        var stringifier = new ExpressionToCLikeSyntax(testSupport.arithmeticPriorities);

        assertEquals(
            "a ? b : c ? d : e",
            stringifier.apply(factory.conditional(
                factory.variableReference("a"),
                factory.variableReference("b"),
                factory.conditional(
                    factory.variableReference("c"),
                    factory.variableReference("d"),
                    factory.variableReference("e")
                )
            ))
        );
    }
}