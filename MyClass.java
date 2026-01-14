import java.util.HashMap;

interface Acceptor<Visitor> {
    void accept(Visitor visitor);
}

// Json Version 1

interface JsonVisitor<JsonType, Visitor extends JsonVisitor<JsonType, Visitor>> {
    void visitObject(JsonObject<JsonType, Visitor> object);

    void visitArray(JsonArray<JsonType, Visitor> array);
}

class JsonObject<JsonType, Visitor extends JsonVisitor<JsonType, Visitor>> implements Acceptor<Visitor> {
    public final HashMap<String, JsonType> map;

    public JsonObject(HashMap<String, JsonType> map) {
        this.map = map;
    }

    public void accept(Visitor visitor) {
        visitor.visitObject(this);
    }
}

class JsonArray<JsonType, Visitor extends JsonVisitor<JsonType, Visitor>> implements Acceptor<Visitor> {
    public final JsonType[] array;

    public JsonArray(JsonType[] array) {
        this.array = array;
    }

    public void accept(Visitor visitor) {
        visitor.visitArray(this);
    }
}

// Recursive Json Version 1

interface Json1Visitor extends JsonVisitor<Json1, Json1Visitor> {
}

interface Json1 extends Acceptor<Json1Visitor> {
}

class JsonArray1 extends JsonArray<Json1, Json1Visitor> implements Json1 {
    public JsonArray1(Json1[] array) {
        super(array);
    }
}

class JsonObject1 extends JsonObject<Json1, Json1Visitor> implements Json1 {
    public JsonObject1(HashMap<String, Json1> map) {
        super(map);
    }
}

// Print Visitor For Version 1

abstract class PrintVisitor<JsonType extends Acceptor<Visitor>, Visitor extends JsonVisitor<JsonType, Visitor>>
        implements JsonVisitor<JsonType, Visitor> {
    abstract protected Visitor getVisitor();

    public void visitObject(JsonObject<JsonType, Visitor> object) {
        System.out.println("Start Object");
        for (JsonType x : object.map.values())
            x.accept(this.getVisitor());
        System.out.println("End Object");
    }

    public void visitArray(JsonArray<JsonType, Visitor> object) {
        System.out.println("Start Array");
        for (JsonType x : object.array)
            x.accept(this.getVisitor());
        System.out.println("End Array");
    }
}

class PrintVisitor1 extends PrintVisitor<Json1, Json1Visitor> implements Json1Visitor {
    protected Json1Visitor getVisitor() {
        return this;
    }
}

// Json Version 2

interface ExtJsonVisitor<JsonType, Visitor extends ExtJsonVisitor<JsonType, Visitor>>
        extends JsonVisitor<JsonType, Visitor> {
    void visitTuple(JsonTuple<JsonType, Visitor> tuple);
}

class JsonTuple<JsonType, Visitor extends ExtJsonVisitor<JsonType, Visitor>> implements Acceptor<Visitor> {
    public final JsonType[] tuple;

    public JsonTuple(JsonType[] tuple) {
        this.tuple = tuple;
    }

    public void accept(Visitor visitor) {
        visitor.visitTuple(this);
    }
}

// Recursive Json Version 2

interface Json2Visitor extends ExtJsonVisitor<Json2, Json2Visitor> {
}

interface Json2 extends Acceptor<Json2Visitor> {
}

class JsonArray2 extends JsonArray<Json2, Json2Visitor> implements Json2 {
    public JsonArray2(Json2[] map) {
        super(map);
    }
}

class JsonObject2 extends JsonObject<Json2, Json2Visitor> implements Json2 {
    public JsonObject2(HashMap<String, Json2> map) {
        super(map);
    }
}

class JsonTuple2 extends JsonTuple<Json2, Json2Visitor> implements Json2 {
    public JsonTuple2(Json2[] map) {
        super(map);
    }
}

// Print Visitor For Version 2

abstract class ExtPrintVisitor<JsonType extends Acceptor<Visitor>, Visitor extends ExtJsonVisitor<JsonType, Visitor>>
        extends PrintVisitor<JsonType, Visitor>
        implements ExtJsonVisitor<JsonType, Visitor> {
    public void visitTuple(JsonTuple<JsonType, Visitor> object) {
        System.out.println("Start Tuple");
        for (JsonType x : object.tuple)
            x.accept(this.getVisitor());
        System.out.println("End Tuple");
    }
}

class PrintVisitor2 extends ExtPrintVisitor<Json2, Json2Visitor> implements Json2Visitor {
    protected Json2Visitor getVisitor() {
        return this;
    }
}

public void main(String args[]) {
    var json = new JsonArray2(new Json2[] { new JsonTuple2(new Json2[] {}), new JsonObject2(new HashMap<>()) });
    json.accept(new PrintVisitor2());
    var json1 = new JsonArray1(new Json1[] { new JsonObject1(new HashMap<>()) });
    json1.accept(new PrintVisitor1());
}