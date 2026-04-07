package spec.visitors;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void localReduceVisitorWalksPreorder() {
        var reducer = new LocalReduceVisitor<>(testSupport.values, (left, right) -> left + "," + right);

        assertEquals(
            "Addition,Literal,FunctionCall,VariableReference,Negation,Literal,VariableReference",
            reducer.apply(testSupport.sampleTraversalExpression())
        );
    }

    @Test
    void isomorphicVisitorReturnsPerTypeValue() {
        var visitor = new IsomorphicGetter<>(new Expressions<>(
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
    void constantFolderUsesExpressionMapper() {
        var folder = new ConstantFolder(factory);

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
        var mapper = new ExpressionMapper(factory, (expression, produce) -> {
            if (expression instanceof VariableReference variableReference) {
                return factory.variableReference(variableReference.name.toUpperCase());
            }
            return produce.apply(expression);
        });

        var result = (Addition) mapper.apply(testSupport.sampleTraversalExpression());
        var call = (FunctionCall) result.right;

        assertEquals("SUM", ((VariableReference) call.callee).name);
        assertEquals("X", ((VariableReference) call.arguments.get(1)).name);
    }
}