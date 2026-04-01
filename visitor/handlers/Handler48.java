package visitor.handlers;
import visitor.expression.*;
public class Handler48 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle48 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle48 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle48 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle48 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle48 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle48 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle48 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle48 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle48 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle48 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle48 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle48 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle48 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle48 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle48 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle48 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle48 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle48 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle48 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle48 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle48 FunctionCall");
                return null;
            }
        });
    }
}