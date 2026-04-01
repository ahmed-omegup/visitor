package visitor.handlers;
import visitor.expression.*;
public class Handler60 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Literal e) {
                Integer i = 60;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(VariableReference e) {
                Integer i = 120;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Addition e) {
                Integer i = 180;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Subtraction e) {
                Integer i = 240;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Multiplication e) {
                Integer i = 300;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Division e) {
                Integer i = 360;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Negation e) {
                Integer i = 420;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Modulo e) {
                Integer i = 480;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Exponentiation e) {
                Integer i = 540;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Equality e) {
                Integer i = 600;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Inequality e) {
                Integer i = 660;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThan e) {
                Integer i = 720;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThan e) {
                Integer i = 780;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThanOrEqual e) {
                Integer i = 840;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThanOrEqual e) {
                Integer i = 900;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conjunction e) {
                Integer i = 960;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Disjunction e) {
                Integer i = 1020;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LogicalNot e) {
                Integer i = 1080;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conditional e) {
                Integer i = 1140;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(FunctionCall e) {
                Integer i = 1200;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}