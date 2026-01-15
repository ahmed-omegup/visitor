package instanceOf.handlers;
import instanceOf.expression.*;
public class Handler67 {
    public Integer handle(Expression v) {
        return switch (v) {
            case Expression1 e -> {
                Integer i = 67;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression2 e -> {
                Integer i = 134;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression3 e -> {
                Integer i = 201;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression4 e -> {
                Integer i = 268;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression5 e -> {
                Integer i = 335;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression6 e -> {
                Integer i = 402;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression7 e -> {
                Integer i = 469;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression8 e -> {
                Integer i = 536;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression9 e -> {
                Integer i = 603;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression10 e -> {
                Integer i = 670;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression11 e -> {
                Integer i = 737;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression12 e -> {
                Integer i = 804;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression13 e -> {
                Integer i = 871;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression14 e -> {
                Integer i = 938;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression15 e -> {
                Integer i = 1005;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression16 e -> {
                Integer i = 1072;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression17 e -> {
                Integer i = 1139;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression18 e -> {
                Integer i = 1206;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression19 e -> {
                Integer i = 1273;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression20 e -> {
                Integer i = 1340;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            default -> throw new RuntimeException();
        };
    }
}