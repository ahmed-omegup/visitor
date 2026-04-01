package visitor.handlers;
import visitor.expression.*;
public class Handler37 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle37 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle37 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle37 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle37 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle37 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle37 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle37 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle37 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle37 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle37 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle37 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle37 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle37 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle37 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle37 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle37 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle37 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle37 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle37 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle37 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle37 FunctionCall");
                return null;
            }
        });
    }
}