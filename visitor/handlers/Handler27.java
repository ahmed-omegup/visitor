package visitor.handlers;
import visitor.expression.*;
public class Handler27 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle27 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle27 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle27 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle27 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle27 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle27 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle27 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle27 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle27 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle27 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle27 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle27 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle27 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle27 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle27 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle27 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle27 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle27 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle27 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle27 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle27 FunctionCall");
                return null;
            }
        });
    }
}