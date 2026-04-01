package visitor.handlers;
import visitor.expression.*;
public class Handler43 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle43 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle43 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle43 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle43 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle43 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle43 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle43 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle43 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle43 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle43 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle43 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle43 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle43 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle43 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle43 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle43 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle43 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle43 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle43 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle43 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle43 FunctionCall");
                return null;
            }
        });
    }
}