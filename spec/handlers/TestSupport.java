package spec.handlers;

import lib.expression.Factory;
import lib.visitors.HandlerFactory;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

final class TestSupport {
    private static final Factory factory = new Factory();
    public static final HandlerFactory v = new HandlerFactory();
    private TestSupport() {}


    static Expression sampleTraversalExpression() {
        return factory.conditional(
            factory.conjunction(
                factory.lessThan(factory.variableReference("x"), factory.literal("10")),
                factory.logicalNot(factory.equality(factory.literal("1"), factory.literal("0")))
            ),
            factory.addition(
                factory.subtraction(factory.literal("7"), factory.literal("2")),
                factory.multiplication(
                    factory.division(factory.literal("8"), factory.literal("2")),
                    factory.modulo(factory.literal("9"), factory.literal("4"))
                )
            ),
            factory.functionCall(
                factory.variableReference("f"),
                factory.exponentiation(factory.literal("2"), factory.literal("3")),
                factory.inequality(factory.literal("5"), factory.literal("6")),
                factory.greaterThan(factory.literal("7"), factory.literal("1")),
                factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")),
                factory.greaterThanOrEqual(factory.literal("3"), factory.literal("3")),
                factory.disjunction(factory.literal("0"), factory.literal("1")),
                factory.negation(factory.literal("4"))
            )
        );
    }

    static List<Expression> sampleNonVariableExpressions() {
        var expressions = new ArrayList<Expression>();
        expressions.add(factory.addition(factory.literal("1"), factory.literal("2")));
        expressions.add(factory.subtraction(factory.literal("3"), factory.literal("1")));
        expressions.add(factory.multiplication(factory.literal("2"), factory.literal("3")));
        expressions.add(factory.division(factory.literal("6"), factory.literal("2")));
        expressions.add(factory.negation(factory.literal("3")));
        expressions.add(factory.modulo(factory.literal("7"), factory.literal("3")));
        expressions.add(factory.exponentiation(factory.literal("2"), factory.literal("3")));
        expressions.add(factory.equality(factory.literal("1"), factory.literal("1")));
        expressions.add(factory.inequality(factory.literal("1"), factory.literal("2")));
        expressions.add(factory.lessThan(factory.literal("1"), factory.literal("2")));
        expressions.add(factory.greaterThan(factory.literal("2"), factory.literal("1")));
        expressions.add(factory.lessThanOrEqual(factory.literal("2"), factory.literal("2")));
        expressions.add(factory.greaterThanOrEqual(factory.literal("2"), factory.literal("2")));
        expressions.add(factory.conjunction(factory.literal("1"), factory.literal("1")));
        expressions.add(factory.disjunction(factory.literal("0"), factory.literal("1")));
        expressions.add(factory.logicalNot(factory.literal("0")));
        expressions.add(factory.conditional(factory.literal("1"), factory.literal("2"), factory.literal("3")));
        expressions.add(factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2")));
        return expressions;
    }
}