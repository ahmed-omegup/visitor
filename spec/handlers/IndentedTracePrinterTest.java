package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.visitors.IndentedTracePrinter;
import port.IFactory;

abstract class IndentedTracePrinterTestBase<E extends Expression> extends TestBase<E> {
    IndentedTracePrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void printsIndentedTraceLines() {
        var expression = factory.addition(factory.literal("1"), factory.negation(factory.literal("2")));

        assertEquals(
            "Addition\n"
                + "  Literal(1)\n"
                + "  Negation\n"
                + "    Literal(2)\n",
            captureOutput(() -> expression.accept(testSupport.v.indentedTracePrinter()))
        );
    }

    @Test
    void visitsAllExpressionKinds() {
        var output = captureOutput(() -> testSupport.sampleTraversalExpression().accept(testSupport.v.indentedTracePrinter()));

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

class IndentedTracePrinterTest extends IndentedTracePrinterTestBase<Expression> {
    IndentedTracePrinterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
