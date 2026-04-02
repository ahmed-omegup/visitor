package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.ExpressionFingerprintReporter;

class ExpressionFingerprintReporterTest {
    @Test
    void reportsNodesDepthHashAndShape() {
        var report = new ExpressionFingerprintReporter().handle(new Addition(new VariableReference("x"), new Literal("2")));

        assertTrue(report.contains("nodes=3;depth=2;hash="));
        assertTrue(report.contains("shape=Addition(VariableReference,Literal)"));
    }
}