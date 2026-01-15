package visitor.handlers;
import visitor.expression.*;
public class Handler2 {
    public void handle(Expression e) {
        e.accept(new Visitor<Void>() {
            public Void visit(Expression1 e) {
                System.out.println("handle2 Expression1");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression1");
                return null;
            }
            public Void visit(Expression2 e) {
                System.out.println("handle2 Expression2");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression2");
                return null;
            }
            public Void visit(Expression3 e) {
                System.out.println("handle2 Expression3");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression3");
                return null;
            }
            public Void visit(Expression4 e) {
                System.out.println("handle2 Expression4");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression4");
                return null;
            }
            public Void visit(Expression5 e) {
                System.out.println("handle2 Expression5");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression5");
                return null;
            }
            public Void visit(Expression6 e) {
                System.out.println("handle2 Expression6");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression6");
                return null;
            }
            public Void visit(Expression7 e) {
                System.out.println("handle2 Expression7");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression7");
                return null;
            }
            public Void visit(Expression8 e) {
                System.out.println("handle2 Expression8");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression8");
                return null;
            }
            public Void visit(Expression9 e) {
                System.out.println("handle2 Expression9");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression9");
                return null;
            }
            public Void visit(Expression10 e) {
                System.out.println("handle2 Expression10");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression10");
                return null;
            }
            public Void visit(Expression11 e) {
                System.out.println("handle2 Expression11");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression11");
                return null;
            }
            public Void visit(Expression12 e) {
                System.out.println("handle2 Expression12");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression12");
                return null;
            }
            public Void visit(Expression13 e) {
                System.out.println("handle2 Expression13");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression13");
                return null;
            }
            public Void visit(Expression14 e) {
                System.out.println("handle2 Expression14");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression14");
                return null;
            }
            public Void visit(Expression15 e) {
                System.out.println("handle2 Expression15");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression15");
                return null;
            }
            public Void visit(Expression16 e) {
                System.out.println("handle2 Expression16");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression16");
                return null;
            }
            public Void visit(Expression17 e) {
                System.out.println("handle2 Expression17");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression17");
                return null;
            }
            public Void visit(Expression18 e) {
                System.out.println("handle2 Expression18");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression18");
                return null;
            }
            public Void visit(Expression19 e) {
                System.out.println("handle2 Expression19");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression19");
                return null;
            }
            public Void visit(Expression20 e) {
                System.out.println("handle2 Expression20");
                for (var x : e.list)
                    x.accept(this);
                System.out.println("end handle2 Expression20");
                return null;
            }
        });
    }
}