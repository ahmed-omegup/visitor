package visitor.handlers;
import visitor.expression.*;
public class Handler92 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Literal e) {
                Integer i = 92;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(VariableReference e) {
                Integer i = 184;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Addition e) {
                Integer i = 276;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Subtraction e) {
                Integer i = 368;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Multiplication e) {
                Integer i = 460;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Division e) {
                Integer i = 552;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Negation e) {
                Integer i = 644;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Modulo e) {
                Integer i = 736;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Exponentiation e) {
                Integer i = 828;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Equality e) {
                Integer i = 920;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Inequality e) {
                Integer i = 1012;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThan e) {
                Integer i = 1104;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThan e) {
                Integer i = 1196;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThanOrEqual e) {
                Integer i = 1288;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThanOrEqual e) {
                Integer i = 1380;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conjunction e) {
                Integer i = 1472;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Disjunction e) {
                Integer i = 1564;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LogicalNot e) {
                Integer i = 1656;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conditional e) {
                Integer i = 1748;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(FunctionCall e) {
                Integer i = 1840;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}