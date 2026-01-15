package visitor.handlers;
import visitor.expression.*;
public class Handler71 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Expression1 e) {
                Integer i = 71;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression2 e) {
                Integer i = 142;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression3 e) {
                Integer i = 213;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression4 e) {
                Integer i = 284;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression5 e) {
                Integer i = 355;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression6 e) {
                Integer i = 426;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression7 e) {
                Integer i = 497;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression8 e) {
                Integer i = 568;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression9 e) {
                Integer i = 639;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression10 e) {
                Integer i = 710;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression11 e) {
                Integer i = 781;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression12 e) {
                Integer i = 852;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression13 e) {
                Integer i = 923;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression14 e) {
                Integer i = 994;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression15 e) {
                Integer i = 1065;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression16 e) {
                Integer i = 1136;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression17 e) {
                Integer i = 1207;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression18 e) {
                Integer i = 1278;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression19 e) {
                Integer i = 1349;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression20 e) {
                Integer i = 1420;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}