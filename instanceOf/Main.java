package instanceOf;

import instanceOf.expression.Expression;
import instanceOf.handlers.*;

public class Main {
    public void main(String args[]) {
        Expression x = Example.x;
        new Handler14().handle(x);
        System.out.println(new Handler53().handle(x) + 15);
    }
}