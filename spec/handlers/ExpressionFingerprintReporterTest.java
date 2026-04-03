package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.ExpressionFingerprintReporter;
import port.IFactory;

class ExpressionFingerprintReporterTest {
    private final IFactory factory = new Factory();
    @Test
    void reportsNodesDepthHashAndShape() {
        var report =factory.addition(factory.variableReference("x"), factory.literal("2")).accept(TestSupport.handlers().expressionFingerprintReporter());

        assertTrue(report.contains("nodes=3;depth=2;hash="));
        assertTrue(report.contains("shape=Addition(VariableReference,Literal)"));
    }
}