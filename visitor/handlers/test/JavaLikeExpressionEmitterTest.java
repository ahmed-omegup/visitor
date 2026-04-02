package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.handlers.JavaLikeExpressionEmitter;

class JavaLikeExpressionEmitterTest {
    @Test
    void emitsTraversalExpressionAsJavaLikeCode() {
        assertEquals(
            "(((x < 10) && (!(1 == 0))) ? ((7 - 2) + ((8 / 2) * (9 % 4))) : f(Math.pow(2, 3), (5 != 6), (7 > 1), (2 <= 2), (3 >= 3), (0 || 1), (-4)))",
            new JavaLikeExpressionEmitter().handle(TestSupport.sampleTraversalExpression())
        );
    }
}