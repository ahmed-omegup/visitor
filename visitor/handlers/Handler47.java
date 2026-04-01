package visitor.handlers;
import visitor.expression.*;
public class Handler47 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle47 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle47 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle47 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle47 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle47 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle47 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle47 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle47 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle47 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle47 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle47 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle47 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle47 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle47 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle47 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle47 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle47 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle47 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle47 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle47 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle47 FunctionCall");
                return null;
            }
        });
    }
}