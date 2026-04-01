package visitor.handlers;
import visitor.expression.*;
public class Handler22 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle22 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle22 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle22 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle22 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle22 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle22 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle22 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle22 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle22 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle22 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle22 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle22 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle22 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle22 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle22 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle22 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle22 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle22 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle22 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle22 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle22 FunctionCall");
                return null;
            }
        });
    }
}