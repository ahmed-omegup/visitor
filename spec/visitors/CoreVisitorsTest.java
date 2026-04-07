package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lib.expression.*;
import lib.visitors.*;

class CoreVisitorsTest {
    private final TestSupport testSupport = new TestSupport();
    private final Factory factory = testSupport.factory;

    @Test
    void childrenUseStructuralOrder() {
        var children = new ExpressionChildren().apply(factory.functionCall(
            factory.variableReference("f"),
            of(factory.literal("1"), factory.variableReference("x"))
        ));

        assertEquals(
            of("VariableReference", "Literal", "VariableReference"),
            children.stream().map(testSupport.typeNames).toList()
        );
    }

    @Test
    void recursiveVisitorWalksPreorder() {
        var order = new ArrayList<String>();

        new RecursiveVisitor(expression -> order.add(testSupport.typeNames.apply(expression)))
            .accept(testSupport.sampleTraversalExpression());

        assertEquals(
            of("Addition", "Literal", "FunctionCall", "VariableReference", "Negation", "Literal", "VariableReference"),
            order
        );
    }

    @Test
    void isomorphicVisitorReturnsPerTypeValue() {
        var visitor = new IsomorphicVisitor<>(new Expressions<>(
            "leaf",
            "leaf",
            "arithmetic",
            "arithmetic",
            "arithmetic",
            "arithmetic",
            "arithmetic",
            "arithmetic",
            "arithmetic",
            "comparison",
            "comparison",
            "comparison",
            "comparison",
            "comparison",
            "comparison",
            "boolean",
            "boolean",
            "boolean",
            "conditional",
            "call"
        ));

        assertEquals("comparison", visitor.apply(factory.lessThan(factory.literal("1"), factory.literal("2"))));
    }

    @Test
    void evaluatorTurnsVisitorIntoFunction() {
        var evaluator = new ExpressionEvaluator<>(testSupport.typeNames);
        assertEquals("Conditional", evaluator.apply(factory.conditional(factory.literal("1"), factory.literal("2"), factory.literal("3"))));
    }

    @Test
    void reducerCombinesNodeThenChildren() {
        var reducer = new ExpressionReducer<>(testSupport.typeNames, (left, right) -> left + "," + right);

        assertEquals(
            "Addition,Literal,FunctionCall,VariableReference,Negation,Literal,VariableReference",
            reducer.apply(testSupport.sampleTraversalExpression())
        );
    }

    @Test
    void folderRebuildsAndAllowsOverrides() {
        class UppercaseVariablesFolder extends ExpressionFolder {
            UppercaseVariablesFolder() { super(factory); }

            public Expression visit(VariableReference expression) {
                return factory.variableReference(expression.name.toUpperCase());
            }
        }

        var result = (Addition) new UppercaseVariablesFolder().apply(testSupport.sampleTraversalExpression());
        var call = (FunctionCall) result.right;

        assertEquals("SUM", ((VariableReference) call.callee).name);
        assertEquals("X", ((VariableReference) call.arguments.get(1)).name);
    }
}