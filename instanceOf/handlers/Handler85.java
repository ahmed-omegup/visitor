package instanceOf.handlers;
import instanceOf.expression.*;
public class Handler85 {
    public Integer handle(Expression v) {
        return switch (v) {
            case Expression1 e -> {
                Integer i = 85;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression2 e -> {
                Integer i = 170;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression3 e -> {
                Integer i = 255;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression4 e -> {
                Integer i = 340;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression5 e -> {
                Integer i = 425;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression6 e -> {
                Integer i = 510;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression7 e -> {
                Integer i = 595;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression8 e -> {
                Integer i = 680;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression9 e -> {
                Integer i = 765;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression10 e -> {
                Integer i = 850;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression11 e -> {
                Integer i = 935;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression12 e -> {
                Integer i = 1020;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression13 e -> {
                Integer i = 1105;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression14 e -> {
                Integer i = 1190;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression15 e -> {
                Integer i = 1275;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression16 e -> {
                Integer i = 1360;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression17 e -> {
                Integer i = 1445;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression18 e -> {
                Integer i = 1530;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression19 e -> {
                Integer i = 1615;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression20 e -> {
                Integer i = 1700;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            default -> throw new RuntimeException();
        };
    }
}