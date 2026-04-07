package spec.visitors;

import static java.util.List.of;

import lib.expression.*;
import lib.visitors.Expressions;
import lib.visitors.IsomorphicVisitor;

final class TestSupport {
    final Factory factory = new Factory();
    final IsomorphicVisitor<String> typeNames = new IsomorphicVisitor<>(new Expressions<>(
        "Literal",
        "VariableReference",
        "Addition",
        "Subtraction",
        "Multiplication",
        "Division",
        "Negation",
        "Modulo",
        "Exponentiation",
        "Equality",
        "Inequality",
        "LessThan",
        "GreaterThan",
        "LessThanOrEqual",
        "GreaterThanOrEqual",
        "Conjunction",
        "Disjunction",
        "LogicalNot",
        "Conditional",
        "FunctionCall"
    ));

    final IsomorphicVisitor<Integer> arithmeticPriorities = new IsomorphicVisitor<>(new Expressions<>(
        100,
        100,
        10,
        10,
        20,
        20,
        30,
        20,
        40,
        5,
        5,
        5,
        5,
        5,
        5,
        3,
        2,
        30,
        1,
        50
    ));

    Expression sampleTraversalExpression() {
        return factory.addition(
            factory.literal("1"),
            factory.functionCall(
                factory.variableReference("sum"),
                of(factory.negation(factory.literal("2")), factory.variableReference("x"))
            )
        );
    }
}