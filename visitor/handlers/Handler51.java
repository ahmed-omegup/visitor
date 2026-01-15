package visitor.handlers;
import visitor.expression.*;
public class Handler51 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Expression1 e) {
                Integer i = 51;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression2 e) {
                Integer i = 102;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression3 e) {
                Integer i = 153;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression4 e) {
                Integer i = 204;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression5 e) {
                Integer i = 255;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression6 e) {
                Integer i = 306;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression7 e) {
                Integer i = 357;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression8 e) {
                Integer i = 408;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression9 e) {
                Integer i = 459;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression10 e) {
                Integer i = 510;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression11 e) {
                Integer i = 561;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression12 e) {
                Integer i = 612;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression13 e) {
                Integer i = 663;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression14 e) {
                Integer i = 714;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression15 e) {
                Integer i = 765;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression16 e) {
                Integer i = 816;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression17 e) {
                Integer i = 867;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression18 e) {
                Integer i = 918;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression19 e) {
                Integer i = 969;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Expression20 e) {
                Integer i = 1020;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}