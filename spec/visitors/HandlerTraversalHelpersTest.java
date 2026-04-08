package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.ExpressionV1;
import lib.expression.ExpressionV2;
import lib.handlers.HandlerFactory;
import lib.handlers.HandlerFactory2;
import port.State;
import port.StateConsumer;

abstract class HandlerTraversalHelpersTestBase<E> extends TestBase<E> {
    HandlerTraversalHelpersTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }

    @Test
    void histogramCountsNodeKindsAcrossTraversalExpression() {
        var histogram = testSupport.v.histogram().apply(testSupport.sampleTraversalExpression());

        assertEquals(22, histogram.literal());
        assertEquals(2, histogram.variableReference());
        assertEquals(1, histogram.addition());
        assertEquals(1, histogram.subtraction());
        assertEquals(1, histogram.multiplication());
        assertEquals(1, histogram.division());
        assertEquals(1, histogram.negation());
        assertEquals(1, histogram.modulo());
        assertEquals(1, histogram.exponentiation());
        assertEquals(1, histogram.equality());
        assertEquals(1, histogram.inequality());
        assertEquals(1, histogram.lessThan());
        assertEquals(1, histogram.greaterThan());
        assertEquals(1, histogram.lessThanOrEqual());
        assertEquals(1, histogram.greaterThanOrEqual());
        assertEquals(1, histogram.conjunction());
        assertEquals(1, histogram.disjunction());
        assertEquals(1, histogram.logicalNot());
        assertEquals(1, histogram.conditional());
        assertEquals(1, histogram.functionCall());
    }

    @Test
    void collectClassNamesVisitorTraversesExpressionInPreOrder() {
        var classNames = testSupport.v.collectClassNamesVisitor().apply(testSupport.sampleTraversalExpression());

        assertEquals(testSupport.expectedTraversalClassNames(), classNames);
    }

    @Test
    void handleStateExposesGetterSetterAndInitialValue() {
        var values = testSupport.v.handleState(new StateConsumer<E, Integer, List<Integer>>() {
            @Override
            public <S> List<Integer> consume(State<E, S, Integer> state) {
                var counts = state.intial(0);
                state.setter(counts, 5).accept(factory.literal("1"));
                state.setter(counts, 2).accept(factory.addition(factory.literal("1"), factory.literal("2")));

                return List.of(
                    state.getter(counts).apply(factory.literal("9")),
                    state.getter(counts).apply(factory.addition(factory.literal("3"), factory.literal("4"))),
                    state.getter(counts).apply(factory.variableReference("x"))
                );
            }
        });

        assertEquals(of(5, 2, 0), values);
    }
}

class HandlerTraversalHelpersTest extends HandlerTraversalHelpersTestBase<ExpressionV1> {
    HandlerTraversalHelpersTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}

class HandlerTraversalHelpersV2Test extends HandlerTraversalHelpersTestBase<ExpressionV2> {
    HandlerTraversalHelpersV2Test() {
        super(new TestSupport<>(new HandlerFactory2()));
    }
}

class HandlerTraversalHelpersV2Support2Test extends HandlerTraversalHelpersTestBase<ExpressionV2> {
    HandlerTraversalHelpersV2Support2Test() {
        super(new TestSupport2<>(new HandlerFactory2()));
    }
}