package instanceOf.handlers;
import instanceOf.expression.*;
public class Handler78 {
    public Integer handle(Expression v) {
        return switch (v) {
            case Expression1 e -> {
                Integer i = 78;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression2 e -> {
                Integer i = 156;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression3 e -> {
                Integer i = 234;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression4 e -> {
                Integer i = 312;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression5 e -> {
                Integer i = 390;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression6 e -> {
                Integer i = 468;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression7 e -> {
                Integer i = 546;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression8 e -> {
                Integer i = 624;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression9 e -> {
                Integer i = 702;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression10 e -> {
                Integer i = 780;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression11 e -> {
                Integer i = 858;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression12 e -> {
                Integer i = 936;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression13 e -> {
                Integer i = 1014;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression14 e -> {
                Integer i = 1092;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression15 e -> {
                Integer i = 1170;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression16 e -> {
                Integer i = 1248;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression17 e -> {
                Integer i = 1326;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression18 e -> {
                Integer i = 1404;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression19 e -> {
                Integer i = 1482;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression20 e -> {
                Integer i = 1560;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            default -> throw new RuntimeException();
        };
    }
}