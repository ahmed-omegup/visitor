package visitor.handlers;
import visitor.expression.*;
public class Handler30 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle30 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle30 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle30 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle30 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle30 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle30 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle30 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle30 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle30 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle30 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle30 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle30 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle30 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle30 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle30 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle30 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle30 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle30 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle30 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle30 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle30 FunctionCall");
                return null;
            }
        });
    }
}