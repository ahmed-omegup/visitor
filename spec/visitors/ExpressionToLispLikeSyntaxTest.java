package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.visitors.ExpressionToLispLikeSyntax;

class ExpressionToLispLikeSyntaxTest {
    private final TestSupport testSupport = new TestSupport();
    private final Factory factory = testSupport.factory;

    @Test
    void lispLikeSyntaxUsesPrefixForms() {
        var stringifier = new ExpressionToLispLikeSyntax();

        assertEquals(
            "(+ 1 (* 2 3))",
            stringifier.apply(factory.addition(factory.literal("1"), factory.multiplication(factory.literal("2"), factory.literal("3"))))
        );
        assertEquals(
            "(if (<= x 10) (f 1 y) (or (not ready) (pow 2 3)))",
            stringifier.apply(factory.conditional(
                factory.lessThanOrEqual(factory.variableReference("x"), factory.literal("10")),
                factory.functionCall(factory.variableReference("f"), java.util.List.of(factory.literal("1"), factory.variableReference("y"))),
                factory.disjunction(factory.logicalNot(factory.variableReference("ready")), factory.exponentiation(factory.literal("2"), factory.literal("3")))
            ))
        );
    }
}