package visitor.handlers;
import visitor.expression.*;
public class Handler32 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle32 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle32 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle32 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle32 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle32 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle32 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle32 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle32 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle32 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle32 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle32 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle32 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle32 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle32 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle32 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle32 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle32 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle32 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle32 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle32 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle32 FunctionCall");
                return null;
            }
        });
    }
}