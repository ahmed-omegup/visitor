package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Division;
import lib.expression.Disjunction;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.FunctionCall;
import lib.expression.GreaterThan;
import lib.expression.GreaterThanOrEqual;
import lib.expression.Inequality;
import lib.expression.LessThan;
import lib.expression.LessThanOrEqual;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Modulo;
import lib.expression.Multiplication;
import lib.expression.Negation;
import lib.expression.Subtraction;
import lib.expression.VariableReference;
import lib.handlers.ZeroDivisionRiskDetector;

class ZeroDivisionRiskDetectorTest {
    @Test
    void detectsLiteralZeroDivisionAndModuloRisk() {
        var detector = new ZeroDivisionRiskDetector();

        assertTrue(detector.handle(lib.expression.Expression.division(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("0"))));
        assertTrue(detector.handle(lib.expression.Expression.modulo(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("0"))));
        assertFalse(detector.handle(lib.expression.Expression.addition(lib.expression.Expression.division(lib.expression.Expression.literal("8"), lib.expression.Expression.variableReference("x")), lib.expression.Expression.literal("1"))));
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
                assertFalse(detector.handle(lib.expression.Expression.division(lib.expression.Expression.literal("8"), expression)));
                assertFalse(detector.handle(lib.expression.Expression.modulo(lib.expression.Expression.literal("8"), expression)));
            }))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> detectsRiskWhenItAppearsOnEitherSideOfOperators() {
        var detector = new ZeroDivisionRiskDetector();
        var risky = lib.expression.Expression.division(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("0"));
        return java.util.List.of(
            lib.expression.Expression.addition(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.addition(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.subtraction(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.subtraction(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.multiplication(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.multiplication(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.exponentiation(risky, lib.expression.Expression.literal("2")),
            lib.expression.Expression.exponentiation(lib.expression.Expression.literal("2"), risky),
            lib.expression.Expression.equality(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.equality(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.inequality(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.inequality(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.lessThan(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.lessThan(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.greaterThan(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.greaterThan(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.lessThanOrEqual(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.lessThanOrEqual(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.greaterThanOrEqual(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.greaterThanOrEqual(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.conjunction(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.conjunction(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.disjunction(lib.expression.Expression.literal("1"), risky),
            lib.expression.Expression.disjunction(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.negation(risky),
            lib.expression.Expression.logicalNot(risky),
            lib.expression.Expression.conditional(risky, lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")),
            lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), risky, lib.expression.Expression.literal("2")),
            lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"), risky),
            lib.expression.Expression.functionCall(risky, lib.expression.Expression.literal("1")),
            lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("sum"), risky)
        ).stream().map(expression -> DynamicTest.dynamicTest("risky-" + expression.getClass().getSimpleName(), () ->
            assertTrue(detector.handle(expression)))).toList();
    }

    @Test
    void ignoresNonZeroLiteralDivisors() {
        var detector = new ZeroDivisionRiskDetector();

        assertFalse(detector.handle(lib.expression.Expression.division(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("2"))));
        assertFalse(detector.handle(lib.expression.Expression.modulo(lib.expression.Expression.literal("8"), lib.expression.Expression.literal("3"))));
    }

    @Test
    void detectsCompositeRiskInDivisorAfterSafePrefixChecks() {
        var detector = new ZeroDivisionRiskDetector();

        assertTrue(detector.handle(lib.expression.Expression.division(lib.expression.Expression.literal("8"), lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.division(lib.expression.Expression.literal("4"), lib.expression.Expression.literal("0"))))));
        assertTrue(detector.handle(lib.expression.Expression.modulo(lib.expression.Expression.literal("8"), lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.division(lib.expression.Expression.literal("4"), lib.expression.Expression.literal("0"))))));
    }
}