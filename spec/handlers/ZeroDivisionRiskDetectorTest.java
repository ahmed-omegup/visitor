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

        assertTrue(detector.handle(lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.literal("0"))));
        assertTrue(detector.handle(lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.literal("0"))));
        assertFalse(detector.handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.variableReference("x")), lib.expression.ExpressionFactory.literal("1"))));
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
                assertFalse(detector.handle(lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("8"), expression)));
                assertFalse(detector.handle(lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("8"), expression)));
            }))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> detectsRiskWhenItAppearsOnEitherSideOfOperators() {
        var detector = new ZeroDivisionRiskDetector();
        var risky = lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.literal("0"));
        return java.util.List.of(
            lib.expression.ExpressionFactory.addition(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.subtraction(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.subtraction(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.multiplication(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.multiplication(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.exponentiation(risky, lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.exponentiation(lib.expression.ExpressionFactory.literal("2"), risky),
            lib.expression.ExpressionFactory.equality(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.equality(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.inequality(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.inequality(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.lessThan(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.lessThan(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.greaterThan(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.greaterThan(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.lessThanOrEqual(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.lessThanOrEqual(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.greaterThanOrEqual(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.greaterThanOrEqual(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.conjunction(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.conjunction(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.disjunction(lib.expression.ExpressionFactory.literal("1"), risky),
            lib.expression.ExpressionFactory.disjunction(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.negation(risky),
            lib.expression.ExpressionFactory.logicalNot(risky),
            lib.expression.ExpressionFactory.conditional(risky, lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), risky, lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"), risky),
            lib.expression.ExpressionFactory.functionCall(risky, lib.expression.ExpressionFactory.literal("1")),
            lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("sum"), risky)
        ).stream().map(expression -> DynamicTest.dynamicTest("risky-" + expression.getClass().getSimpleName(), () ->
            assertTrue(detector.handle(expression)))).toList();
    }

    @Test
    void ignoresNonZeroLiteralDivisors() {
        var detector = new ZeroDivisionRiskDetector();

        assertFalse(detector.handle(lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.literal("2"))));
        assertFalse(detector.handle(lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.literal("3"))));
    }

    @Test
    void detectsCompositeRiskInDivisorAfterSafePrefixChecks() {
        var detector = new ZeroDivisionRiskDetector();

        assertTrue(detector.handle(lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("4"), lib.expression.ExpressionFactory.literal("0"))))));
        assertTrue(detector.handle(lib.expression.ExpressionFactory.modulo(lib.expression.ExpressionFactory.literal("8"), lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.division(lib.expression.ExpressionFactory.literal("4"), lib.expression.ExpressionFactory.literal("0"))))));
    }
}