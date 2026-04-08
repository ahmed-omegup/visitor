package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.dict.ConstDict;
import lib.expression.ExpressionV1;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.utils.EitherVisitor;
import port.State;
import port.StateConsumer;

abstract class HandlerFactoryHelpersTestBase<E> extends TestBase<E> {
    HandlerFactoryHelpersTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }

    @Test
    void bindingPowerHandlerUsesExpectedPriorities() {
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

    @Test
    void constantFolderOnceHandlesFoldableOperatorsAndShortCircuit() {
        var foldOnce = testSupport.v.constantFolderOnce();

        assertEquals("3", render(foldOnce.apply(factory.addition(factory.literal("1"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.subtraction(factory.literal("3"), factory.literal("2")))));
        assertEquals("6", render(foldOnce.apply(factory.multiplication(factory.literal("3"), factory.literal("2")))));
        assertEquals("2", render(foldOnce.apply(factory.division(factory.literal("6"), factory.literal("3")))));
        assertEquals("1", render(foldOnce.apply(factory.modulo(factory.literal("7"), factory.literal("3")))));
        assertEquals("8", render(foldOnce.apply(factory.exponentiation(factory.literal("2"), factory.literal("3")))));
        assertEquals("-2", render(foldOnce.apply(factory.negation(factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.equality(factory.literal("2"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.inequality(factory.literal("2"), factory.literal("3")))));
        assertEquals("1", render(foldOnce.apply(factory.lessThan(factory.literal("2"), factory.literal("3")))));
        assertEquals("1", render(foldOnce.apply(factory.greaterThan(factory.literal("3"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")))));
        assertEquals("1", render(foldOnce.apply(factory.greaterThanOrEqual(factory.literal("3"), factory.literal("3")))));
        assertEquals("0", render(foldOnce.apply(factory.conjunction(factory.literal("0"), factory.variableReference("x")))));
        assertEquals("y", render(foldOnce.apply(factory.conjunction(factory.literal("1"), factory.variableReference("y")))));
        assertEquals("1", render(foldOnce.apply(factory.disjunction(factory.literal("1"), factory.variableReference("x")))));
        assertEquals("y", render(foldOnce.apply(factory.disjunction(factory.literal("0"), factory.variableReference("y")))));
        assertEquals("1", render(foldOnce.apply(factory.logicalNot(factory.literal("0")))));
        assertEquals("22", render(foldOnce.apply(factory.conditional(factory.literal("0"), factory.literal("11"), factory.literal("22")))));
        assertEquals("f(1)", render(foldOnce.apply(factory.functionCall(factory.variableReference("f"), of(factory.literal("1"))))));
        assertEquals("x", render(foldOnce.apply(factory.variableReference("x"))));
        assertEquals("4", render(foldOnce.apply(factory.literal("4"))));
    }
}

class HandlerFactoryHelpersTest extends HandlerFactoryHelpersTestBase<ExpressionV1> {
    private final HandlerFactory handlers = new HandlerFactory();

    HandlerFactoryHelpersTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }

    @Test
    void literalAndVariableDetectorsReturnEitherShapes() {
        var literalResult = handlers.isLiteral().apply(factory.literal("8")).accept(
            new EitherVisitor<Literal<ExpressionV1>, ExpressionV1, String>() {
                public String left(Literal<ExpressionV1> left) {
                    return left.value;
                }

                public String right(ExpressionV1 right) {
                    return "right";
                }
            }
        );

        var variableResult = handlers.isVariable().apply(factory.variableReference("x")).accept(
            new EitherVisitor<VariableReference<ExpressionV1>, ExpressionV1, String>() {
                public String left(VariableReference<ExpressionV1> left) {
                    return left.name;
                }

                public String right(ExpressionV1 right) {
                    return "right";
                }
            }
        );

        var nonLiteralResult = handlers.isLiteral().apply(factory.addition(factory.literal("1"), factory.literal("2"))).accept(
            new EitherVisitor<Literal<ExpressionV1>, ExpressionV1, String>() {
                public String left(Literal<ExpressionV1> left) {
                    return "left";
                }

                public String right(ExpressionV1 right) {
                    return handlers.expressionClassNameExtractor().apply(right);
                }
            }
        );

        assertEquals("8", literalResult);
        assertEquals("x", variableResult);
        assertEquals("Addition", nonLiteralResult);
    }

    @Test
    void reducersAndStateHelpersProduceExpectedValues() {
        var local = handlers.localReduceVisitor(0, (_count, _expression) -> 1)
            .apply(testSupport.sampleTraversalExpression());
        var global = handlers.globalReduceVisitor(new ConstDict<>(1), Integer::sum)
            .apply(testSupport.sampleTraversalExpression());
        var seeded = handlers.handleState(new StateConsumer<ExpressionV1, Integer, Integer>() {
            public <S> Integer consume(State<ExpressionV1, S, Integer> state) {
                S values = state.intial(7);
                state.setter(values, 99).accept(factory.variableReference("changed"));
                return state.getter(values).apply(factory.variableReference("changed"));
            }
        });

        assertNotNull(local);
        assertEquals(42, global);
        assertEquals(99, seeded);
    }
}