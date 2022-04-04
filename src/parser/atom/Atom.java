package parser.atom;

import org.json.JSONObject;
import parser.DataType;
import parser.ToJsonObject;

public abstract class Atom implements ToJsonObject {
    private String value;

    protected DataType type = DataType.Invalid;

    public Atom(DataType type) {
        this.type = type;
    }

    public DataType getType() {
        return type;
    }

    public Atom(DataType type, String value) {
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

