package visitor.handlers;
import visitor.expression.*;
public class Handler49 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle49 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle49 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle49 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle49 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle49 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle49 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle49 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle49 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle49 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle49 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle49 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle49 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle49 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle49 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle49 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle49 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle49 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle49 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle49 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle49 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle49 FunctionCall");
                return null;
            }
        });
    }
}