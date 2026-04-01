package visitor.handlers;
import visitor.expression.*;
public class Handler39 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle39 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle39 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle39 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle39 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle39 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle39 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle39 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle39 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle39 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle39 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle39 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle39 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle39 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle39 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle39 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle39 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle39 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle39 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle39 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle39 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle39 FunctionCall");
                return null;
            }
        });
    }
}