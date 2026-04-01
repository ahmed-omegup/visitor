package visitor.handlers;
import visitor.expression.*;
public class Handler46 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Literal e) {
                System.out.println("handle46 Literal");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Literal");
                return null;
            }
            public Void visit(VariableReference e) {
                System.out.println("handle46 VariableReference");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 VariableReference");
                return null;
            }
            public Void visit(Addition e) {
                System.out.println("handle46 Addition");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Addition");
                return null;
            }
            public Void visit(Subtraction e) {
                System.out.println("handle46 Subtraction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Subtraction");
                return null;
            }
            public Void visit(Multiplication e) {
                System.out.println("handle46 Multiplication");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Multiplication");
                return null;
            }
            public Void visit(Division e) {
                System.out.println("handle46 Division");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Division");
                return null;
            }
            public Void visit(Negation e) {
                System.out.println("handle46 Negation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Negation");
                return null;
            }
            public Void visit(Modulo e) {
                System.out.println("handle46 Modulo");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Modulo");
                return null;
            }
            public Void visit(Exponentiation e) {
                System.out.println("handle46 Exponentiation");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Exponentiation");
                return null;
            }
            public Void visit(Equality e) {
                System.out.println("handle46 Equality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Equality");
                return null;
            }
            public Void visit(Inequality e) {
                System.out.println("handle46 Inequality");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Inequality");
                return null;
            }
            public Void visit(LessThan e) {
                System.out.println("handle46 LessThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 LessThan");
                return null;
            }
            public Void visit(GreaterThan e) {
                System.out.println("handle46 GreaterThan");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 GreaterThan");
                return null;
            }
            public Void visit(LessThanOrEqual e) {
                System.out.println("handle46 LessThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 LessThanOrEqual");
                return null;
            }
            public Void visit(GreaterThanOrEqual e) {
                System.out.println("handle46 GreaterThanOrEqual");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 GreaterThanOrEqual");
                return null;
            }
            public Void visit(Conjunction e) {
                System.out.println("handle46 Conjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Conjunction");
                return null;
            }
            public Void visit(Disjunction e) {
                System.out.println("handle46 Disjunction");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Disjunction");
                return null;
            }
            public Void visit(LogicalNot e) {
                System.out.println("handle46 LogicalNot");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 LogicalNot");
                return null;
            }
            public Void visit(Conditional e) {
                System.out.println("handle46 Conditional");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 Conditional");
                return null;
            }
            public Void visit(FunctionCall e) {
                System.out.println("handle46 FunctionCall");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle46 FunctionCall");
                return null;
            }
        });
    }
}