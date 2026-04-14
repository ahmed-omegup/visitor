package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HandlerPredicatesAndBindingTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void literalAndVariableCheckersRecognizeNodeKinds(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        assertTrue(testSupport.v.literalChecker().apply(factory.literal("8")));
        assertFalse(testSupport.v.literalChecker().apply(factory.addition(factory.literal("1"), factory.literal("2"))));
        assertTrue(testSupport.v.variableChecker().apply(factory.variableReference("x")));
        assertFalse(testSupport.v.variableChecker().apply(factory.negation(factory.literal("3"))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void bindingPowerHandlerUsesExpectedPriorities(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
        var bindingPower = testSupport.v.createBindingPowerHandler();
        var addition = bindingPower.apply(factory.addition(factory.literal("1"), factory.literal("2")));
        var exponentiation = bindingPower.apply(factory.exponentiation(factory.literal("2"), factory.literal("3")));
        var conditional = bindingPower.apply(testSupport.sampleTraversalExpression());

        assertEquals(10, addition.priority());
        assertFalse(addition.isRightAssociative());
        assertEquals(40, exponentiation.priority());
        assertTrue(exponentiation.isRightAssociative());
        assertEquals(1, conditional.priority());
    }
}