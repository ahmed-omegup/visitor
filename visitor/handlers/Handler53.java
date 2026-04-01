package visitor.handlers;
import visitor.expression.*;
public class Handler53 {
    public Integer handle(Expression e) {
        return e.accept(new Visitor<Integer>() {
            public Integer visit(Literal e) {
                Integer i = 53;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(VariableReference e) {
                Integer i = 106;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Addition e) {
                Integer i = 159;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Subtraction e) {
                Integer i = 212;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Multiplication e) {
                Integer i = 265;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Division e) {
                Integer i = 318;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Negation e) {
                Integer i = 371;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Modulo e) {
                Integer i = 424;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Exponentiation e) {
                Integer i = 477;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Equality e) {
                Integer i = 530;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Inequality e) {
                Integer i = 583;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThan e) {
                Integer i = 636;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThan e) {
                Integer i = 689;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LessThanOrEqual e) {
                Integer i = 742;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(GreaterThanOrEqual e) {
                Integer i = 795;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conjunction e) {
                Integer i = 848;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Disjunction e) {
                Integer i = 901;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(LogicalNot e) {
                Integer i = 954;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(Conditional e) {
                Integer i = 1007;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
            public Integer visit(FunctionCall e) {
                Integer i = 1060;
                for (var x : e.list)
                    i += x.accept(this);
                return i;
            }
        });
    }
}