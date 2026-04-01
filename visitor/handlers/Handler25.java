package visitor.handlers;
import visitor.expression.*;
public class Handler25 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle25 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle25 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle25 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle25 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle25 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle25 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle25 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle25 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle25 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle25 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle25 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle25 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle25 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle25 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle25 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle25 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle25 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle25 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle25 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle25 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle25 FunctionCall");
                return null;
            }
        });
    }
}