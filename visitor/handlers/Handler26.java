package visitor.handlers;
import visitor.expression.*;
public class Handler26 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle26 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle26 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle26 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle26 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle26 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle26 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle26 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle26 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle26 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle26 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle26 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle26 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle26 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle26 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle26 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle26 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle26 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle26 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle26 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle26 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle26 FunctionCall");
                return null;
            }
        });
    }
}