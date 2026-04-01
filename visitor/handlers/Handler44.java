package visitor.handlers;
import visitor.expression.*;
public class Handler44 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle44 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle44 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle44 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle44 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle44 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle44 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle44 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle44 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle44 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle44 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle44 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle44 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle44 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle44 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle44 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle44 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle44 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle44 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle44 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle44 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle44 FunctionCall");
                return null;
            }
        });
    }
}