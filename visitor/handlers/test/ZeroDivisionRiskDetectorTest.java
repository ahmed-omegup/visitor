package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Division;
import visitor.expression.Disjunction;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.FunctionCall;
import visitor.expression.GreaterThan;
import visitor.expression.GreaterThanOrEqual;
import visitor.expression.Inequality;
import visitor.expression.LessThan;
import visitor.expression.LessThanOrEqual;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Modulo;
import visitor.expression.Multiplication;
import visitor.expression.Negation;
import visitor.expression.Subtraction;
import visitor.expression.VariableReference;
import visitor.handlers.ZeroDivisionRiskDetector;

class ZeroDivisionRiskDetectorTest {
    @Test
    void detectsLiteralZeroDivisionAndModuloRisk() {
        var detector = new ZeroDivisionRiskDetector();

        assertTrue(detector.handle(new Division(new Literal("8"), new Literal("0"))));
        assertTrue(detector.handle(new Modulo(new Literal("8"), new Literal("0"))));
        assertFalse(detector.handle(new Addition(new Division(new Literal("8"), new VariableReference("x")), new Literal("1"))));
    }

    @Test
    void detectsRiskInsideTraversalTree() {
        assertFalse(new ZeroDivisionRiskDetector().handle(TestSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> treatsNonLiteralDivisorsAsSafeCandidatesAcrossExpressionKinds() {
        var detector = new ZeroDivisionRiskDetector();
        return TestSupport.sampleNonVariableExpressions().stream()
            .map(expression -> DynamicTest.dynamicTest("divisor-" + expression.getClass().getSimpleName(), () -> {
                assertFalse(detector.handle(new Division(new Literal("8"), expression)));
                assertFalse(detector.handle(new Modulo(new Literal("8"), expression)));
            }))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> detectsRiskWhenItAppearsOnEitherSideOfOperators() {
        var detector = new ZeroDivisionRiskDetector();
        var risky = new Division(new Literal("8"), new Literal("0"));
        return java.util.List.of(
            new Addition(risky, new Literal("1")),
            new Addition(new Literal("1"), risky),
            new Subtraction(risky, new Literal("1")),
            new Multiplication(new Literal("1"), risky),
            new Exponentiation(risky, new Literal("2")),
            new Equality(new Literal("1"), risky),
            new Inequality(risky, new Literal("1")),
            new LessThan(risky, new Literal("1")),
            new GreaterThan(new Literal("1"), risky),
            new LessThanOrEqual(risky, new Literal("1")),
            new GreaterThanOrEqual(new Literal("1"), risky),
            new Conjunction(risky, new Literal("1")),
            new Disjunction(new Literal("1"), risky),
            new Negation(risky),
            new LogicalNot(risky),
            new Conditional(risky, new Literal("1"), new Literal("2")),
            new Conditional(new Literal("1"), risky, new Literal("2")),
            new Conditional(new Literal("1"), new Literal("2"), risky),
            new FunctionCall(risky, new Literal("1")),
            new FunctionCall(new VariableReference("sum"), risky)
        ).stream().map(expression -> DynamicTest.dynamicTest("risky-" + expression.getClass().getSimpleName(), () ->
            assertTrue(detector.handle(expression)))).toList();
    }

    @Test
    void ignoresNonZeroLiteralDivisors() {
        var detector = new ZeroDivisionRiskDetector();

        assertFalse(detector.handle(new Division(new Literal("8"), new Literal("2"))));
        assertFalse(detector.handle(new Modulo(new Literal("8"), new Literal("3"))));
    }
}