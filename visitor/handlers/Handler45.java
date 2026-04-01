package visitor.handlers;
import visitor.expression.*;
public class Handler45 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle45 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle45 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle45 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle45 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle45 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle45 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle45 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle45 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle45 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle45 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle45 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle45 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle45 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle45 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle45 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle45 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle45 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle45 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle45 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle45 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle45 FunctionCall");
                return null;
            }
        });
    }
}