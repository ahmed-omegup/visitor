package visitor.handlers;
import visitor.expression.*;
public class Handler29 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle29 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle29 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle29 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle29 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle29 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle29 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle29 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle29 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle29 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle29 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle29 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle29 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle29 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle29 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle29 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle29 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle29 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle29 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle29 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle29 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle29 FunctionCall");
                return null;
            }
        });
    }
}