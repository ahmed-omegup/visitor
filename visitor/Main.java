package visitor;

import visitor.handlers.*;
import static visitor.Factory.*;

public class Main {
    public void main(String args[]) {
        var x = conditional(
            lessThan(variableReference("threshold"), literal("10")),
            addition(variableReference("threshold"), literal("1")),
            functionCall(variableReference("fallback"), literal("0"))
        );
        System.out.println(new LiteralCollector().handle(x));
        System.out.println(new ExpressionSummaryReporter().handle(x));
    }
}