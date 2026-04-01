package visitor.handlers;
import visitor.expression.*;
public class Handler38 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle38 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle38 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle38 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle38 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle38 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle38 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle38 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle38 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle38 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle38 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle38 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle38 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle38 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle38 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle38 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle38 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle38 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle38 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle38 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle38 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle38 FunctionCall");
                return null;
            }
        });
    }
}