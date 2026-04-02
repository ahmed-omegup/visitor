package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;
import lib.handlers.ZeroDivisionRiskDetector;

class ZeroDivisionRiskDetectorTest {
    @Test
    void detectsLiteralZeroDivisionAndModuloRisk() {
        var detector = new ZeroDivisionRiskDetector();

        assertTrue(detector.handle(division(literal("8"), literal("0"))));
        assertTrue(detector.handle(modulo(literal("8"), literal("0"))));
        assertFalse(detector.handle(addition(division(literal("8"), variableReference("x")), literal("1"))));
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
                assertFalse(detector.handle(division(literal("8"), expression)));
                assertFalse(detector.handle(modulo(literal("8"), expression)));
            }))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> detectsRiskWhenItAppearsOnEitherSideOfOperators() {
        var detector = new ZeroDivisionRiskDetector();
        var risky = division(literal("8"), literal("0"));
        return java.util.List.of(
            addition(risky, literal("1")),
            addition(literal("1"), risky),
            subtraction(risky, literal("1")),
            subtraction(literal("1"), risky),
            multiplication(literal("1"), risky),
            multiplication(risky, literal("1")),
            exponentiation(risky, literal("2")),
            exponentiation(literal("2"), risky),
            equality(literal("1"), risky),
            equality(risky, literal("1")),
            inequality(risky, literal("1")),
            inequality(literal("1"), risky),
            lessThan(risky, literal("1")),
            lessThan(literal("1"), risky),
            greaterThan(literal("1"), risky),
            greaterThan(risky, literal("1")),
            lessThanOrEqual(risky, literal("1")),
            lessThanOrEqual(literal("1"), risky),
            greaterThanOrEqual(literal("1"), risky),
            greaterThanOrEqual(risky, literal("1")),
            conjunction(risky, literal("1")),
            conjunction(literal("1"), risky),
            disjunction(literal("1"), risky),
            disjunction(risky, literal("1")),
            negation(risky),
            logicalNot(risky),
            conditional(risky, literal("1"), literal("2")),
            conditional(literal("1"), risky, literal("2")),
            conditional(literal("1"), literal("2"), risky),
            functionCall(risky, literal("1")),
            functionCall(variableReference("sum"), risky)
        ).stream().map(expression -> DynamicTest.dynamicTest("risky-" + expression.getClass().getSimpleName(), () ->
            assertTrue(detector.handle(expression)))).toList();
    }

    @Test
    void ignoresNonZeroLiteralDivisors() {
        var detector = new ZeroDivisionRiskDetector();

        assertFalse(detector.handle(division(literal("8"), literal("2"))));
        assertFalse(detector.handle(modulo(literal("8"), literal("3"))));
    }

    @Test
    void detectsCompositeRiskInDivisorAfterSafePrefixChecks() {
        var detector = new ZeroDivisionRiskDetector();

        assertTrue(detector.handle(division(literal("8"), addition(literal("1"), division(literal("4"), literal("0"))))));
        assertTrue(detector.handle(modulo(literal("8"), addition(literal("1"), division(literal("4"), literal("0"))))));
    }
}