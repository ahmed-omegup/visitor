package visitor.handlers;
import visitor.expression.*;
public class Handler95 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Literal e) {
                Integer i = 95;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(VariableReference e) {
                Integer i = 190;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Addition e) {
                Integer i = 285;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Subtraction e) {
                Integer i = 380;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Multiplication e) {
                Integer i = 475;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Division e) {
                Integer i = 570;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Negation e) {
                Integer i = 665;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Modulo e) {
                Integer i = 760;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Exponentiation e) {
                Integer i = 855;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Equality e) {
                Integer i = 950;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Inequality e) {
                Integer i = 1045;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThan e) {
                Integer i = 1140;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThan e) {
                Integer i = 1235;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThanOrEqual e) {
                Integer i = 1330;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThanOrEqual e) {
                Integer i = 1425;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conjunction e) {
                Integer i = 1520;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Disjunction e) {
                Integer i = 1615;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LogicalNot e) {
                Integer i = 1710;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conditional e) {
                Integer i = 1805;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(FunctionCall e) {
                Integer i = 1900;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}