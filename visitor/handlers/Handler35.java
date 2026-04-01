package visitor.handlers;
import visitor.expression.*;
public class Handler35 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle35 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle35 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle35 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle35 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle35 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle35 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle35 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle35 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle35 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle35 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle35 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle35 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle35 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle35 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle35 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle35 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle35 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle35 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle35 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle35 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle35 FunctionCall");
                return null;
            }
        });
    }
}