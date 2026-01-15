package instanceOf.handlers;
import instanceOf.expression.*;
public class Handler80 {
    public Integer handle(Expression v) {
        return switch (v) {
            case Expression1 e -> {
                Integer i = 80;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression2 e -> {
                Integer i = 160;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression3 e -> {
                Integer i = 240;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression4 e -> {
                Integer i = 320;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression5 e -> {
                Integer i = 400;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression6 e -> {
                Integer i = 480;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression7 e -> {
                Integer i = 560;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression8 e -> {
                Integer i = 640;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression9 e -> {
                Integer i = 720;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression10 e -> {
                Integer i = 800;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression11 e -> {
                Integer i = 880;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression12 e -> {
                Integer i = 960;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression13 e -> {
                Integer i = 1040;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression14 e -> {
                Integer i = 1120;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression15 e -> {
                Integer i = 1200;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression16 e -> {
                Integer i = 1280;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression17 e -> {
                Integer i = 1360;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression18 e -> {
                Integer i = 1440;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression19 e -> {
                Integer i = 1520;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            case Expression20 e -> {
                Integer i = 1600;
                for (var x : e.list)
                    i += this.handle(x);
                yield i;
            }
            default -> throw new RuntimeException();
        };
    }
}