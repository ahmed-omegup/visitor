package instanceOf.handlers;
import instanceOf.expression.*;
public class Handler72 {
    public Integer handle(Expression v) {
        return switch (v) {
            case Expression1 e -> {
                Integer i = 72;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression2 e -> {
                Integer i = 144;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression3 e -> {
                Integer i = 216;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression4 e -> {
                Integer i = 288;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression5 e -> {
                Integer i = 360;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression6 e -> {
                Integer i = 432;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression7 e -> {
                Integer i = 504;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression8 e -> {
                Integer i = 576;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression9 e -> {
                Integer i = 648;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression10 e -> {
                Integer i = 720;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression11 e -> {
                Integer i = 792;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression12 e -> {
                Integer i = 864;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression13 e -> {
                Integer i = 936;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression14 e -> {
                Integer i = 1008;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression15 e -> {
                Integer i = 1080;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression16 e -> {
                Integer i = 1152;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression17 e -> {
                Integer i = 1224;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression18 e -> {
                Integer i = 1296;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression19 e -> {
                Integer i = 1368;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression20 e -> {
                Integer i = 1440;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            default -> throw new RuntimeException();
        };
    }
}