import java.util.HashMap;

interface Acceptor<Visitor> {
    void accept(Visitor visitor);
}


// Recursive Json Version 1

interface Json1Visitor {
    void visitObject(JsonObject1 object);

    void visitArray(JsonArray1 array);
}

interface Json1 extends Acceptor<Json1Visitor> {
    void accept(Json1Visitor visitor);
}

class JsonArray1 implements Json1 {
    public final Json1[] array;

    public JsonArray1(Json1[] array) {
        this.array = array;
    }

    public void accept(Json1Visitor visitor) {
        visitor.visitArray(this);
    }
}

class JsonObject1 implements Json1 {
    public final HashMap<String, Json1> map;

    public JsonObject1(HashMap<String, Json1> map) {
        this.map = map;
    }

    public void accept(Json1Visitor visitor) {
        visitor.visitObject(this);
    }
}

// Print Visitor For Version 1

class PrintVisitor1 implements Json1Visitor {

    public void visitObject(JsonObject1 object) {
        System.out.println("Start Object");
        for (Json1 x : object.map.values())
            x.accept(this);
        System.out.println("End Object");
    }

    public void visitArray(JsonArray1 object) {
        System.out.println("Start Array");
        for (Json1 x : object.array)
            x.accept(this);
        System.out.println("End Array");
    }
}


void main(String args[]) {
    var json1 = new JsonArray1(new Json1[] { new JsonObject1(new HashMap<>()) });
    json1.accept(new PrintVisitor1());
}