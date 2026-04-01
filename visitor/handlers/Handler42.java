package visitor.handlers;
import visitor.expression.*;
public class Handler42 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle42 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle42 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle42 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle42 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle42 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle42 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle42 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle42 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle42 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle42 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle42 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle42 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle42 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle42 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle42 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle42 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle42 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle42 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle42 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle42 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle42 FunctionCall");
                return null;
            }
        });
    }
}