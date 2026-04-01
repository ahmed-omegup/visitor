package visitor.handlers;
import visitor.expression.*;
public class Handler40 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle40 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle40 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle40 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle40 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle40 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle40 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle40 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle40 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle40 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle40 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle40 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle40 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle40 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle40 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle40 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle40 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle40 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle40 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle40 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle40 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle40 FunctionCall");
                return null;
            }
        });
    }
}