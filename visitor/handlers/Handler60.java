package visitor.handlers;
import visitor.expression.*;
public class Handler60 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Expression1 e) {
                Integer i = 60;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression2 e) {
                Integer i = 120;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression3 e) {
                Integer i = 180;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression4 e) {
                Integer i = 240;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression5 e) {
                Integer i = 300;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression6 e) {
                Integer i = 360;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression7 e) {
                Integer i = 420;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression8 e) {
                Integer i = 480;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression9 e) {
                Integer i = 540;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression10 e) {
                Integer i = 600;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression11 e) {
                Integer i = 660;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression12 e) {
                Integer i = 720;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression13 e) {
                Integer i = 780;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression14 e) {
                Integer i = 840;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression15 e) {
                Integer i = 900;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression16 e) {
                Integer i = 960;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression17 e) {
                Integer i = 1020;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression18 e) {
                Integer i = 1080;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression19 e) {
                Integer i = 1140;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression20 e) {
                Integer i = 1200;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}