package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import lib.expression.Expression;
import lib.expressions.Expressions;
import lib.handlers.HandlerFactory;

abstract class CoreVisitorsTestBase<E> extends TestBase<E> {
    CoreVisitorsTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }

    @Test
    void childrenUseStructuralOrder() {
        var children = testSupport.v.expressionChildren().apply(factory.functionCall(
            factory.variableReference("f"),
            of(factory.literal("1"), factory.variableReference("x"))
        ));

        assertEquals(
            of("VariableReference", "Literal", "VariableReference"),
            children.stream().map(testSupport.v.expressionClassNameExtractor()).toList()
        );
    }

    @Test
    void arithmeticDepthHistogramComesFromHandlers() {
        var histogram = testSupport.v.arithmeticDepthHistogramBuilder().apply(
            factory.addition(
                factory.multiplication(factory.literal("2"), factory.literal("3")),
                factory.negation(factory.literal("4"))
            )
        );

        assertEquals(Map.of(0, 1, 1, 2), histogram);
    }

    @Test
    void localReduceVisitorWalksPreorder() {
        var reduced = new AtomicReference<String>("");
        var reducer = testSupport.v.localReduceVisitor(testSupport.values, (left, right) -> {
            var value = reduced.get();
            var next = value.isEmpty() ? left : value + "," + left;
            reduced.set(next);
            return left;
        });

        reducer.accept(testSupport.sampleTraversalExpression());

        assertEquals(
            "Conditional,Conjunction,LessThan,VariableReference,Literal,LogicalNot,Equality,Literal,Literal,Addition,Subtraction,Literal,Literal,Multiplication,Division,Literal,Literal,Modulo,Literal,Literal,FunctionCall,VariableReference,Exponentiation,Literal,Literal,Inequality,Literal,Literal,GreaterThan,Literal,Literal,LessThanOrEqual,Literal,Literal,GreaterThanOrEqual,Literal,Literal,Disjunction,Literal,Literal,Negation,Literal",
            reduced.get()
        );
    }

    @Test
    void isomorphicVisitorReturnsPerTypeValue() {
        var values = new Expressions<String>();
        values.literal = "leaf";
        values.variableReference = "leaf";
        values.addition = "arithmetic";
        values.subtraction = "arithmetic";
        values.multiplication = "arithmetic";
        values.division = "arithmetic";
        values.negation = "arithmetic";
        values.modulo = "arithmetic";
        values.exponentiation = "arithmetic";
        values.equality = "comparison";
        values.inequality = "comparison";
        values.lessThan = "comparison";
        values.greaterThan = "comparison";
        values.lessThanOrEqual = "comparison";
        values.greaterThanOrEqual = "comparison";
        values.conjunction = "boolean";
        values.disjunction = "boolean";
        values.logicalNot = "boolean";
        values.conditional = "conditional";
        values.functionCall = "call";
        var visitor = testSupport.v.isomorphicGetter(values);

        assertEquals("comparison", visitor.apply(factory.lessThan(factory.literal("1"), factory.literal("2"))));
    }

    @Test
    void constantFolderUsesExpressionMapper() {
        var folder = testSupport.v.constantFolder();
        var foldedArithmetic = folder.apply(factory.addition(factory.literal("2"), factory.literal("3")));

        assertEquals("Literal", typeName(foldedArithmetic));
        assertEquals("5", render(foldedArithmetic));

        var foldedConditional = folder.apply(factory.conditional(
            factory.literal("0"),
            factory.literal("10"),
            factory.addition(factory.literal("1"), factory.literal("2"))
        ));

        assertEquals("Literal", typeName(foldedConditional));
        assertEquals("3", render(foldedConditional));
    }

    @Test
    void mapperSupportsCloneAndOverrides() {
        var mapper = testSupport.v.expressionMapper((expression, produce) -> {
            if ("VariableReference".equals(typeName(expression))) {
                return factory.variableReference(render(expression).toUpperCase());
            }
            return produce.get();
        });

        assertEquals(
            "X < 10 && !(1 == 0) ? 7 - 2 + 8 / 2 * (9 % 4) : F(pow(2, 3), 5 != 6, 7 > 1, 2 <= 2, 3 >= 3, 0 || 1, -4)",
            render(mapper.apply(testSupport.sampleTraversalExpression()))
        );
    }
}

class CoreVisitorsTest extends CoreVisitorsTestBase<Expression> {
    CoreVisitorsTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}