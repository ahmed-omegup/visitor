package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.ExpressionFingerprintReporter;

class ExpressionFingerprintReporterTest {
    @Test
    void reportsNodesDepthHashAndShape() {
        var report = new ExpressionFingerprintReporter().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("2")));

        assertTrue(report.contains("nodes=3;depth=2;hash="));
        assertTrue(report.contains("shape=Addition(VariableReference,Literal)"));
    }
}