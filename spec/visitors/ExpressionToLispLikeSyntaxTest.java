package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.handlers.HandlerFactory;

abstract class ExpressionToLispLikeSyntaxTestBase<E> extends TestBase<E> {
    ExpressionToLispLikeSyntaxTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }

    @Test
    void lispLikeSyntaxUsesPrefixForms() {
        assertEquals(
            "(+ 1 (* 2 3))",
            renderLispLike(factory.addition(factory.literal("1"), factory.multiplication(factory.literal("2"), factory.literal("3"))))
        );
        assertEquals(
            "(if (<= x 10) (f 1 y) (or (not ready) (pow 2 3)))",
            renderLispLike(factory.conditional(
                factory.lessThanOrEqual(factory.variableReference("x"), factory.literal("10")),
                factory.functionCall(factory.variableReference("f"), java.util.List.of(factory.literal("1"), factory.variableReference("y"))),
                factory.disjunction(factory.logicalNot(factory.variableReference("ready")), factory.exponentiation(factory.literal("2"), factory.literal("3")))
            ))
        );
    }
}

class ExpressionToLispLikeSyntaxTest extends ExpressionToLispLikeSyntaxTestBase<ExpressionV1> {
    ExpressionToLispLikeSyntaxTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}