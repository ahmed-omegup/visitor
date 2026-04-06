package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;
import lib.handlers.HandlerFactory;
import lib.handlers.ZeroDivisionRiskDetector;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import lib.expression.*;

abstract class ZeroDivisionRiskDetectorTestBase<E> extends TestBase<E> {
    ZeroDivisionRiskDetectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void detectsLiteralZeroDivisionAndModuloRisk() {
        var detector = testSupport.v.zeroDivisionRiskDetector();

        assertTrue(detector.apply(factory.division(factory.literal("8"), factory.literal("0"))));
        assertTrue(detector.apply(factory.modulo(factory.literal("8"), factory.literal("0"))));
        assertFalse(detector.apply(factory.addition(factory.division(factory.literal("8"), factory.variableReference("x")), factory.literal("1"))));
    }

    @Test
    void detectsRiskInsideTraversalTree() {
        assertFalse(testSupport.v.zeroDivisionRiskDetector().apply(testSupport.sampleTraversalExpression()));
    }

    @TestFactory
    Iterable<DynamicTest> treatsNonLiteralDivisorsAsSafeCandidatesAcrossExpressionKinds() {
        var detector = testSupport.v.zeroDivisionRiskDetector();
        return testSupport.sampleNonVariableExpressions().stream()
            .map(expression -> DynamicTest.dynamicTest("divisor-" + typeName(expression), () -> {
                assertFalse(detector.apply(factory.division(factory.literal("8"), expression)));
                assertFalse(detector.apply(factory.modulo(factory.literal("8"), expression)));
            }))
            .toList();
    }

    @TestFactory
    Iterable<DynamicTest> detectsRiskWhenItAppearsOnEitherSideOfOperators() {
        var detector = testSupport.v.zeroDivisionRiskDetector();
        var risky = factory.division(factory.literal("8"), factory.literal("0"));
        return of(
            factory.addition(risky, factory.literal("1")),
            factory.addition(factory.literal("1"), risky),
            factory.subtraction(risky, factory.literal("1")),
            factory.subtraction(factory.literal("1"), risky),
            factory.multiplication(factory.literal("1"), risky),
            factory.multiplication(risky, factory.literal("1")),
            factory.exponentiation(risky, factory.literal("2")),
            factory.exponentiation(factory.literal("2"), risky),
            factory.equality(factory.literal("1"), risky),
            factory.equality(risky, factory.literal("1")),
            factory.inequality(risky, factory.literal("1")),
            factory.inequality(factory.literal("1"), risky),
            factory.lessThan(risky, factory.literal("1")),
            factory.lessThan(factory.literal("1"), risky),
            factory.greaterThan(factory.literal("1"), risky),
            factory.greaterThan(risky, factory.literal("1")),
            factory.lessThanOrEqual(risky, factory.literal("1")),
            factory.lessThanOrEqual(factory.literal("1"), risky),
            factory.greaterThanOrEqual(factory.literal("1"), risky),
            factory.greaterThanOrEqual(risky, factory.literal("1")),
            factory.conjunction(risky, factory.literal("1")),
            factory.conjunction(factory.literal("1"), risky),
            factory.disjunction(factory.literal("1"), risky),
            factory.disjunction(risky, factory.literal("1")),
            factory.negation(risky),
            factory.logicalNot(risky),
            factory.conditional(risky, factory.literal("1"), factory.literal("2")),
            factory.conditional(factory.literal("1"), risky, factory.literal("2")),
            factory.conditional(factory.literal("1"), factory.literal("2"), risky),
            factory.functionCall(risky, of( factory.literal("1"))),
            factory.functionCall(factory.variableReference("sum"), of( risky))
        ).stream().map(expression -> DynamicTest.dynamicTest("risky-" + typeName(expression), () ->
            assertTrue(detector.apply(expression)))).toList();
    }

    @Test
    void ignoresNonZeroLiteralDivisors() {
        var detector = testSupport.v.zeroDivisionRiskDetector();

        assertFalse(detector.apply(factory.division(factory.literal("8"), factory.literal("2"))));
        assertFalse(detector.apply(factory.modulo(factory.literal("8"), factory.literal("3"))));
    }

    @Test
    void detectsCompositeRiskInDivisorAfterSafePrefixChecks() {
        var detector = testSupport.v.zeroDivisionRiskDetector();

        assertTrue(detector.apply(factory.division(factory.literal("8"), factory.addition(factory.literal("1"), factory.division(factory.literal("4"), factory.literal("0"))))));
        assertTrue(detector.apply(factory.modulo(factory.literal("8"), factory.addition(factory.literal("1"), factory.division(factory.literal("4"), factory.literal("0"))))));
    }
}

class ZeroDivisionRiskDetectorTest extends ZeroDivisionRiskDetectorTestBase<Expression> {
    ZeroDivisionRiskDetectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
