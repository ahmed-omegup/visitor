package spec.visitors;

import static java.util.List.of;

import lib.expression.*;
import lib.visitors.ExpressionToCLikeSyntax.BindingPower;
import lib.visitors.Expressions;
import lib.visitors.IsomorphicGetter;

final class TestSupport {
    final Factory factory = new Factory();
    final Expressions<String> values = new Expressions<>(
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
    );
    final IsomorphicGetter<String> typeNames = new IsomorphicGetter<>(new Expressions<>(
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

    final IsomorphicGetter<BindingPower> arithmeticPriorities = new IsomorphicGetter<>(new Expressions<>(
        new BindingPower(100, false),
        new BindingPower(100, false),
        new BindingPower(10, false),
        new BindingPower(10, false),
        new BindingPower(20, false),
        new BindingPower(20, false),
        new BindingPower(30, true),
        new BindingPower(20, false),
        new BindingPower(40, true),
        new BindingPower(5, false),
        new BindingPower(5, false),
        new BindingPower(5, false),
        new BindingPower(5, false),
        new BindingPower(5, false),
        new BindingPower(5, false),
        new BindingPower(3, false),
        new BindingPower(2, false),
        new BindingPower(30, true),
        new BindingPower(1, true),
        new BindingPower(50, false)
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