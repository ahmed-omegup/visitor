package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.ExpressionFingerprintReporter;
import lib.legacy.HandlerFactory;

abstract class ExpressionFingerprintReporterTestBase<E> extends TestBase<E> {
    ExpressionFingerprintReporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void reportsNodesDepthHashAndShape() {
        var report =testSupport.v.expressionFingerprintReporter().apply(factory.addition(factory.variableReference("x"), factory.literal("2")));

        assertTrue(report.contains("nodes=3;depth=2;hash="));
        assertTrue(report.contains("shape=Addition(VariableReference,Literal)"));
    }
}

class ExpressionFingerprintReporterTest extends ExpressionFingerprintReporterTestBase<Expression> {
    ExpressionFingerprintReporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
