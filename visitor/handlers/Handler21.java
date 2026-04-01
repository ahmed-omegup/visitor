package visitor.handlers;
import visitor.expression.*;
public class Handler21 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle21 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle21 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle21 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle21 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle21 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle21 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle21 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle21 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle21 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle21 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle21 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle21 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle21 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle21 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle21 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle21 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle21 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle21 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle21 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle21 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle21 FunctionCall");
                return null;
            }
        });
    }
}