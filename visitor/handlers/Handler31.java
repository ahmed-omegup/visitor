package visitor.handlers;
import visitor.expression.*;
public class Handler31 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle31 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle31 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle31 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle31 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle31 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle31 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle31 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle31 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle31 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle31 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle31 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle31 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle31 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle31 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle31 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle31 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle31 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle31 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle31 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle31 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle31 FunctionCall");
                return null;
            }
        });
    }
}