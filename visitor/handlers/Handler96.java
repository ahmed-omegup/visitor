package visitor.handlers;
import visitor.expression.*;
public class Handler96 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Expression1 e) {
                Integer i = 96;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression2 e) {
                Integer i = 192;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression3 e) {
                Integer i = 288;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression4 e) {
                Integer i = 384;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression5 e) {
                Integer i = 480;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression6 e) {
                Integer i = 576;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression7 e) {
                Integer i = 672;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression8 e) {
                Integer i = 768;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression9 e) {
                Integer i = 864;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression10 e) {
                Integer i = 960;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression11 e) {
                Integer i = 1056;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression12 e) {
                Integer i = 1152;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression13 e) {
                Integer i = 1248;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression14 e) {
                Integer i = 1344;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression15 e) {
                Integer i = 1440;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression16 e) {
                Integer i = 1536;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression17 e) {
                Integer i = 1632;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression18 e) {
                Integer i = 1728;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression19 e) {
                Integer i = 1824;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression20 e) {
                Integer i = 1920;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}