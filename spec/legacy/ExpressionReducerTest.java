package spec.legacy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.Expression;
import lib.legacy.ExpressionReducer;
import lib.legacy.HandlerFactory;

class ExpressionReducerTest extends TestBase<Expression> {
    ExpressionReducerTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }

    @Test
    void reducesSingleNodeExpression() {
        var reducer = new ExpressionReducer<String>(
            testSupport.v.expressionClassNameExtractor(),
            (left, right) -> left + "," + right
        );

        assertEquals("Literal", reducer.apply(factory.literal("1")));
    }

    @Test
    void reducesInPreorderAcrossChildren() {
        var reducer = new ExpressionReducer<String>(
            testSupport.v.expressionClassNameExtractor(),
            (left, right) -> left + "," + right
        );

        assertEquals(
            "Addition,Literal,VariableReference",
            reducer.apply(factory.addition(factory.literal("1"), factory.variableReference("x")))
        );
    }

    @Test
    void reducesFunctionCallCalleeBeforeArguments() {
        var reducer = new ExpressionReducer<String>(
            testSupport.v.expressionClassNameExtractor(),
            (left, right) -> left + "," + right
        );

        assertEquals(
            "FunctionCall,VariableReference,Addition,Literal,Literal",
            reducer.apply(factory.functionCall(
                factory.variableReference("sum"),
                of(factory.addition(factory.literal("1"), factory.literal("2")))
            ))
        );
    }
}