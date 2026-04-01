package visitor.handlers;
import visitor.expression.*;
public class Handler24 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle24 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle24 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle24 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle24 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle24 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle24 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle24 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle24 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle24 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle24 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle24 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle24 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle24 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle24 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle24 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle24 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle24 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle24 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle24 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle24 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle24 FunctionCall");
                return null;
            }
        });
    }
}