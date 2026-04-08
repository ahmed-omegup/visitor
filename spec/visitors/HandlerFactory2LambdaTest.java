package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.ExpressionV2;
import lib.handlers.HandlerFactory2;
import port.IExpressionFactory2;
import port.State;
import port.StateConsumer;

class HandlerFactory2LambdaTest extends TestBase<ExpressionV2> {
    private final HandlerFactory2 handler;
    private final IExpressionFactory2<ExpressionV2> factory2;

    HandlerFactory2LambdaTest() {
        super(new TestSupport2<>(new HandlerFactory2()));
        this.handler = (HandlerFactory2) testSupport.v;
        this.factory2 = handler.expressionFactory();
    }

    @Test
    void lambdaSpecificPrintersUseDedicatedSyntax() {
        var lambda = factory2.lambdaExpression(
            "n",
            factory2.addition(factory2.variableReference("n"), factory2.literal("1"))
        );

        assertEquals("n => n + 1", renderJsLike(lambda));
        assertEquals("(lambda (n) (+ n 1))", renderLispLike(lambda));
    }

    @Test
    void histogram2CountsLambdaNodeSeparately() {
        var lambda = factory2.lambdaExpression(
            "n",
            factory2.addition(factory2.variableReference("n"), factory2.literal("1"))
        );
        var histogram = handler.histogram2().apply(lambda);

        assertEquals(1, histogram.lambdaExpression());
        assertEquals(1, histogram.addition());
        assertEquals(1, histogram.variableReference());
        assertEquals(1, histogram.literal());
        assertEquals(0, histogram.negation());
    }

    @Test
    void lambdaHelpersExposeExpectedMetadata() {
        var lambda = factory2.lambdaExpression(
            "n",
            factory2.addition(factory2.variableReference("n"), factory2.literal("1"))
        );

        assertEquals("LambdaExpression", handler.expressionClassNameExtractor().apply(lambda));
        assertEquals(List.of("LambdaExpression", "Addition", "VariableReference", "Literal"), handler.collectClassNamesVisitor().apply(lambda));
        assertEquals(List.of(render(factory2.addition(factory2.variableReference("n"), factory2.literal("1")))),
            handler.expressionChildren().apply(lambda).stream().map(this::render).toList());
        assertFalse(handler.literalChecker().apply(lambda));
        assertFalse(handler.variableChecker().apply(lambda));
        assertEquals(30, handler.createBindingPowerHandler().apply(lambda).priority());
    }

    @Test
    void stateAndDictLookupHandleLambdaBranch() {
        var lambda = factory2.lambdaExpression("n", factory2.literal("1"));

        var values = handler.handleState(new StateConsumer<ExpressionV2, Integer, List<Integer>>() {
            @Override
            public <S> List<Integer> consume(State<ExpressionV2, S, Integer> state) {
                var counts = state.intial(0);
                state.setter(counts, 7).accept(lambda);
                return List.of(state.getter(counts).apply(lambda), state.getter(counts).apply(factory2.literal("1")));
            }
        });

        assertEquals(List.of(7, 0), values);
    }

    @Test
    void i18nAndConstantFolderExposeLambdaSurface() {
        var lambda = factory2.lambdaExpression(
            "n",
            factory2.addition(factory2.variableReference("n"), factory2.literal("1"))
        );
        var english = handler.i18nDict("en");

        assertNotNull(english);
        assertEquals("lambdaExpression", english.apply(lambda));
        assertEquals(null, handler.i18nDict("xx"));
        assertSame(lambda, handler.constantFolderOnce().apply(lambda));
    }

    @Test
    void histogram2CountsEveryWrappedExpressionKind() {
        var expressions = Map.ofEntries(
            Map.entry("Literal", factory2.literal("1")),
            Map.entry("VariableReference", factory2.variableReference("x")),
            Map.entry("Addition", factory2.addition(factory2.literal("1"), factory2.literal("2"))),
            Map.entry("Subtraction", factory2.subtraction(factory2.literal("3"), factory2.literal("1"))),
            Map.entry("Multiplication", factory2.multiplication(factory2.literal("2"), factory2.literal("4"))),
            Map.entry("Division", factory2.division(factory2.literal("8"), factory2.literal("2"))),
            Map.entry("Negation", factory2.negation(factory2.literal("5"))),
            Map.entry("LambdaExpression", factory2.lambdaExpression("n", factory2.literal("1"))),
            Map.entry("Modulo", factory2.modulo(factory2.literal("9"), factory2.literal("4"))),
            Map.entry("Exponentiation", factory2.exponentiation(factory2.literal("2"), factory2.literal("3"))),
            Map.entry("Equality", factory2.equality(factory2.literal("1"), factory2.literal("1"))),
            Map.entry("Inequality", factory2.inequality(factory2.literal("1"), factory2.literal("2"))),
            Map.entry("LessThan", factory2.lessThan(factory2.literal("1"), factory2.literal("2"))),
            Map.entry("GreaterThan", factory2.greaterThan(factory2.literal("2"), factory2.literal("1"))),
            Map.entry("LessThanOrEqual", factory2.lessThanOrEqual(factory2.literal("1"), factory2.literal("1"))),
            Map.entry("GreaterThanOrEqual", factory2.greaterThanOrEqual(factory2.literal("2"), factory2.literal("1"))),
            Map.entry("Conjunction", factory2.conjunction(factory2.literal("1"), factory2.literal("0"))),
            Map.entry("Disjunction", factory2.disjunction(factory2.literal("1"), factory2.literal("0"))),
            Map.entry("LogicalNot", factory2.logicalNot(factory2.literal("0"))),
            Map.entry("Conditional", factory2.conditional(factory2.literal("1"), factory2.literal("2"), factory2.literal("3"))),
            Map.entry("FunctionCall", factory2.functionCall(factory2.variableReference("f"), List.of(factory2.literal("1"), factory2.literal("2"))))
        );

        for (var entry : expressions.entrySet()) {
            var histogram = handler.histogram2().apply(entry.getValue());
            assertEquals(1, countFor(entry.getKey(), histogram), entry.getKey());
        }
    }

    @Test
    void integerEvaluatorRejectsDirectLambdaEvaluation() {
        var lambda = factory2.lambdaExpression(
            "n",
            factory2.addition(factory2.variableReference("n"), factory2.literal("1"))
        );
        var evaluator = handler.integerEvaluator(Map.of(), Map.of());

        var exception = assertThrows(IllegalArgumentException.class, () -> evaluator.apply(lambda));

        assertEquals("Cannot directly evaluate a lambda expression", exception.getMessage());
    }

    @Test
    void histogram2RejectsUnknownExpressionKinds() {
        ExpressionV2 malformed = new ExpressionV2() {
            @Override
            @SuppressWarnings("unchecked")
            public <R> R accept(lib.expression.ExpressionVisitor2<R> visitor) {
                return (R) "Mystery";
            }
        };

        var exception = assertThrows(IllegalArgumentException.class, () -> handler.histogram2().apply(malformed));

        assertEquals("Unknown expression kind: Mystery", exception.getMessage());
    }

    private int countFor(String expressionKind, port.IExpressionDict2<Integer> histogram) {
        return switch (expressionKind) {
            case "Literal" -> histogram.literal();
            case "VariableReference" -> histogram.variableReference();
            case "Addition" -> histogram.addition();
            case "Subtraction" -> histogram.subtraction();
            case "Multiplication" -> histogram.multiplication();
            case "Division" -> histogram.division();
            case "Negation" -> histogram.negation();
            case "LambdaExpression" -> histogram.lambdaExpression();
            case "Modulo" -> histogram.modulo();
            case "Exponentiation" -> histogram.exponentiation();
            case "Equality" -> histogram.equality();
            case "Inequality" -> histogram.inequality();
            case "LessThan" -> histogram.lessThan();
            case "GreaterThan" -> histogram.greaterThan();
            case "LessThanOrEqual" -> histogram.lessThanOrEqual();
            case "GreaterThanOrEqual" -> histogram.greaterThanOrEqual();
            case "Conjunction" -> histogram.conjunction();
            case "Disjunction" -> histogram.disjunction();
            case "LogicalNot" -> histogram.logicalNot();
            case "Conditional" -> histogram.conditional();
            case "FunctionCall" -> histogram.functionCall();
            default -> throw new IllegalArgumentException("Unknown expression kind: " + expressionKind);
        };
    }
}