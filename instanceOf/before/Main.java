package instanceOf.before;

import instanceOf.before.expression.*;
import instanceOf.before.handlers.*;

public class Main {
    public void main(String args[]) {
        var x = new Expression1(new Expression[] {
            new Expression18(new Expression[]{
                new Expression13(new Expression[]{}),
            }),
            new Expression2(new Expression[]{}),
        });
        new Handler14().handle(x);
        System.out.println(new Handler53().handle(x) + 15);
    }
}