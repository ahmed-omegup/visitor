package visitor.handlers;
import visitor.expression.*;
public class Handler56 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Expression1 e) {
                Integer i = 56;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression2 e) {
                Integer i = 112;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression3 e) {
                Integer i = 168;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression4 e) {
                Integer i = 224;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression5 e) {
                Integer i = 280;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression6 e) {
                Integer i = 336;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression7 e) {
                Integer i = 392;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression8 e) {
                Integer i = 448;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression9 e) {
                Integer i = 504;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression10 e) {
                Integer i = 560;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression11 e) {
                Integer i = 616;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression12 e) {
                Integer i = 672;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression13 e) {
                Integer i = 728;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression14 e) {
                Integer i = 784;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression15 e) {
                Integer i = 840;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression16 e) {
                Integer i = 896;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression17 e) {
                Integer i = 952;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression18 e) {
                Integer i = 1008;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression19 e) {
                Integer i = 1064;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression20 e) {
                Integer i = 1120;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}