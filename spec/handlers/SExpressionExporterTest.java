package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.SExpressionExporter;

class SExpressionExporterTest {
    @Test
    void exportsAllExpressionTypesAsSExpressions() {
        var exporter = TestSupport.handlers().sExpressionExporter();

        assertEquals(
            "(Conditional (Conjunction (LessThan x 10) (LogicalNot (Equality 1 0))) (Addition (Subtraction 7 2) (Multiplication (Division 8 2) (Modulo 9 4))) (FunctionCall f (Exponentiation 2 3) (Inequality 5 6) (GreaterThan 7 1) (LessThanOrEqual 2 2) (GreaterThanOrEqual 3 3) (Disjunction 0 1) (Negation 4)))",
TestSupport.sampleTraversalExpression().accept(exporter)
        );
    }
}