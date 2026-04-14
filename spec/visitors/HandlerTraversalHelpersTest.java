package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import port.State;
import port.StateConsumer;

class HandlerTraversalHelpersTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void histogramCountsNodeKindsAcrossTraversalExpression(String variant, TestSupport<E> testSupport) {
        var histogram = testSupport.v.histogram().apply(testSupport.sampleTraversalExpression());

        assertEquals(testSupport.expectedTraversalHistogramCounts(), testSupport.histogramCounts(histogram));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void collectClassNamesVisitorTraversesExpressionInPreOrder(String variant, TestSupport<E> testSupport) {
        var classNames = testSupport.v.collectClassNamesVisitor().apply(testSupport.sampleTraversalExpression());

        assertEquals(testSupport.expectedTraversalClassNames(), classNames);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("spec.visitors.TestVariants#all")
    <E> void handleStateExposesGetterSetterAndInitialValue(String variant, TestSupport<E> testSupport) {
        var factory = testSupport.factory;
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