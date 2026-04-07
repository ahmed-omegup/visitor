package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.expressions.Expressions;
import lib.handlers.HandlerFactory;
import lib.visitors.*;

class CoreVisitorsTest extends TestBase<Expression> {
    CoreVisitorsTest() {
        super(new TestSupport<>(new HandlerFactory()));
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
        var reducer = new lib.handlers.LocalReduceVisitor<>(testSupport.values, (left, right) -> left + "," + right);

        assertEquals(
            "Addition,Literal,FunctionCall,VariableReference,Negation,Literal,VariableReference",
            reducer.apply(testSupport.sampleTraversalExpression())
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
        var visitor = new IsomorphicGetter<>(values);

        assertEquals("comparison", visitor.apply(factory.lessThan(factory.literal("1"), factory.literal("2"))));
    }

    @Test
    void constantFolderUsesExpressionMapper() {
        var folder = testSupport.v.constantFolder();

        assertEquals(
            "5",
            ((Literal) folder.apply(factory.addition(factory.literal("2"), factory.literal("3")))).value
        );

        var conditional = folder.apply(factory.conditional(
            factory.literal("0"),
            factory.literal("10"),
            factory.addition(factory.literal("1"), factory.literal("2"))
        ));

        assertEquals("3", ((Literal) conditional).value);
    }

    @Test
    void mapperSupportsCloneAndOverrides() {
        var mapper = testSupport.v.expressionMapper((expression, produce) -> {
            if (expression instanceof VariableReference variableReference) {
                return factory.variableReference(variableReference.name.toUpperCase());
            }
            return produce.get();
        });

        var result = (Addition) mapper.apply(testSupport.sampleTraversalExpression());
        var call = (FunctionCall) result.right;

        assertEquals("SUM", ((VariableReference) call.callee).name);
        assertEquals("X", ((VariableReference) call.arguments.get(1)).name);
    }
}