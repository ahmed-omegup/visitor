package visitor.handlers;
import visitor.expression.*;
public class Handler34 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle34 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle34 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle34 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle34 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle34 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle34 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle34 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle34 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle34 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle34 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle34 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle34 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle34 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle34 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle34 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle34 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle34 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle34 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle34 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle34 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle34 FunctionCall");
                return null;
            }
        });
    }
}