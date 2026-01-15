package instanceOf.handlers;
import instanceOf.expression.*;
public class Handler4 {
    public void handle(Expression v) {
        switch (v) {
            case Expression1 e -> {
                System.out.println("handle4 Expression1");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression1");
            }
            case Expression2 e -> {
                System.out.println("handle4 Expression2");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression2");
            }
            case Expression3 e -> {
                System.out.println("handle4 Expression3");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression3");
            }
            case Expression4 e -> {
                System.out.println("handle4 Expression4");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression4");
            }
            case Expression5 e -> {
                System.out.println("handle4 Expression5");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression5");
            }
            case Expression6 e -> {
                System.out.println("handle4 Expression6");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression6");
            }
            case Expression7 e -> {
                System.out.println("handle4 Expression7");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression7");
            }
            case Expression8 e -> {
                System.out.println("handle4 Expression8");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression8");
            }
            case Expression9 e -> {
                System.out.println("handle4 Expression9");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression9");
            }
            case Expression10 e -> {
                System.out.println("handle4 Expression10");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression10");
            }
            case Expression11 e -> {
                System.out.println("handle4 Expression11");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression11");
            }
            case Expression12 e -> {
                System.out.println("handle4 Expression12");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression12");
            }
            case Expression13 e -> {
                System.out.println("handle4 Expression13");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression13");
            }
            case Expression14 e -> {
                System.out.println("handle4 Expression14");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression14");
            }
            case Expression15 e -> {
                System.out.println("handle4 Expression15");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression15");
            }
            case Expression16 e -> {
                System.out.println("handle4 Expression16");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression16");
            }
            case Expression17 e -> {
                System.out.println("handle4 Expression17");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression17");
            }
            case Expression18 e -> {
                System.out.println("handle4 Expression18");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression18");
            }
            case Expression19 e -> {
                System.out.println("handle4 Expression19");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression19");
            }
            case Expression20 e -> {
                System.out.println("handle4 Expression20");
                for (var x : e.list)
                    this.handle(x);
                System.out.println("end handle4 Expression20");
            }
            default -> throw new RuntimeException();
        }
    }
}