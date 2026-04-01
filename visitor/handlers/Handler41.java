package visitor.handlers;
import visitor.expression.*;
public class Handler41 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle41 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle41 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle41 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle41 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle41 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle41 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle41 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle41 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle41 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle41 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle41 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle41 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle41 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle41 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle41 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle41 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle41 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle41 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle41 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle41 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle41 FunctionCall");
                return null;
            }
        });
    }
}