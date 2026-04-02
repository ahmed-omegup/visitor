package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.handlers.IndentedTracePrinter;

class IndentedTracePrinterTest {
    @Test
    void printsIndentedTraceLines() {
        var expression = lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("2")));

        assertEquals(
            "Addition\n"
                + "  Literal(1)\n"
                + "  Negation\n"
                + "    Literal(2)\n",
            captureOutput(() -> new IndentedTracePrinter().handle(expression))
        );
    }

    @Test
    void visitsAllExpressionKinds() {
        var output = captureOutput(() -> new IndentedTracePrinter().handle(TestSupport.sampleTraversalExpression()));

        assertTrue(output.contains("LessThanOrEqual\n"));
        assertTrue(output.contains("GreaterThanOrEqual\n"));
        assertTrue(output.contains("Disjunction\n"));
    }

    private String captureOutput(Runnable action) {
        var original = System.out;
        var buffer = new ByteArrayOutputStream();
        var stream = new PrintStream(buffer, true, StandardCharsets.UTF_8);
        try {
            System.setOut(stream);
            action.run();
            return buffer.toString(StandardCharsets.UTF_8);
        } finally {
            System.setOut(original);
            stream.close();
        }
    }
}