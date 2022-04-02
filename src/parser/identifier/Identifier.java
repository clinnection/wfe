package parser.identifier;

import org.json.JSONObject;
import parser.ToJsonObject;

public abstract class Identifier implements ToJsonObject {

    enum Type {
        Invalid,
        Var,
        Json
    };

    protected Type type = Type.Invalid;

    public Identifier(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("type", type);
        return jsonObject;
    }
}
