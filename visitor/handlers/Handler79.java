package visitor.handlers;
import visitor.expression.*;
public class Handler79 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Literal e) {
                Integer i = 79;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(VariableReference e) {
                Integer i = 158;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Addition e) {
                Integer i = 237;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Subtraction e) {
                Integer i = 316;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Multiplication e) {
                Integer i = 395;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Division e) {
                Integer i = 474;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Negation e) {
                Integer i = 553;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Modulo e) {
                Integer i = 632;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Exponentiation e) {
                Integer i = 711;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Equality e) {
                Integer i = 790;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Inequality e) {
                Integer i = 869;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThan e) {
                Integer i = 948;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThan e) {
                Integer i = 1027;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThanOrEqual e) {
                Integer i = 1106;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThanOrEqual e) {
                Integer i = 1185;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conjunction e) {
                Integer i = 1264;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Disjunction e) {
                Integer i = 1343;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LogicalNot e) {
                Integer i = 1422;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conditional e) {
                Integer i = 1501;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(FunctionCall e) {
                Integer i = 1580;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}