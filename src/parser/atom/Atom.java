package parser.atom;

import org.json.JSONObject;
import parser.ToJsonObject;

public abstract class Atom implements ToJsonObject {
    private String value;

    enum Type {
        Invalid,
        Boolean,
        Integer,
        Decimal,
        String,
        Var
    };

    protected Atom.Type type = Atom.Type.Invalid;

    public Atom(Atom.Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Atom(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("type", type);
        jsonObject.putOnce("value", value);
        return jsonObject;
    }
}

