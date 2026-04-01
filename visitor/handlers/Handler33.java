package visitor.handlers;
import visitor.expression.*;
public class Handler33 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle33 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle33 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle33 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle33 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle33 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle33 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle33 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle33 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle33 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle33 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle33 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle33 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle33 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle33 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle33 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle33 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle33 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle33 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle33 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle33 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle33 FunctionCall");
                return null;
            }
        });
    }
}