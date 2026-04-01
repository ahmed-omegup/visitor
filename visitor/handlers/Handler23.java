package visitor.handlers;
import visitor.expression.*;
public class Handler23 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle23 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle23 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle23 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle23 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle23 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle23 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle23 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle23 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle23 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle23 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle23 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle23 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle23 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle23 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle23 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle23 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle23 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle23 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle23 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle23 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle23 FunctionCall");
                return null;
            }
        });
    }
}