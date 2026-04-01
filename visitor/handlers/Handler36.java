package visitor.handlers;
import visitor.expression.*;
public class Handler36 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle36 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle36 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle36 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle36 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle36 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle36 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle36 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle36 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle36 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle36 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle36 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle36 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle36 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle36 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle36 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle36 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle36 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle36 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle36 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle36 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle36 FunctionCall");
                return null;
            }
        });
    }
}