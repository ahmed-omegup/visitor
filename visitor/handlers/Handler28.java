package visitor.handlers;
import visitor.expression.*;
public class Handler28 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle28 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle28 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle28 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle28 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle28 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle28 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle28 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle28 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle28 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle28 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle28 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle28 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle28 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle28 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle28 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle28 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle28 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle28 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle28 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle28 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle28 FunctionCall");
                return null;
            }
        });
    }
}