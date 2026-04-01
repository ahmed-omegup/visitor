package visitor.handlers;
import visitor.expression.*;
public class Handler50 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle50 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle50 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle50 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle50 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle50 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle50 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle50 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle50 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle50 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle50 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle50 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle50 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle50 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle50 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle50 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle50 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle50 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle50 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle50 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle50 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle50 FunctionCall");
                return null;
            }
        });
    }
}