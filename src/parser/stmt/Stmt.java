package parser.stmt;

import org.json.JSONObject;
import parser.ToJsonObject;

public abstract class Stmt implements ToJsonObject {

    enum Type {
        Invalid,
        If,
        ElseIf,
        Else,
        While,
        VarAssignment,
        Program
    };

    protected Stmt.Type type = Stmt.Type.Invalid;

    public Stmt(Type type) {
        this.type = type;
    }

    public Stmt.Type getType() {
        return type;
    }

    public void setType(Stmt.Type type) {
        this.type = type;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("type", type);
        return jsonObject;
    }
}

