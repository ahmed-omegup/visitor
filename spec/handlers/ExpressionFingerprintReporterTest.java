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
        var report = new ExpressionFingerprintReporter().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("2")));

        assertTrue(report.contains("nodes=3;depth=2;hash="));
        assertTrue(report.contains("shape=Addition(VariableReference,Literal)"));
    }
}