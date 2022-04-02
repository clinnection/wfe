package parser.expr;

import org.json.JSONObject;

public abstract class Expr {

    enum Type {
        Invalid,
        Add,
        Atom,
        Comp,
        Logic,
        Mult,
        Neg,
        Not,
        Paren
    };

    protected Expr.Type type = Expr.Type.Invalid;

    public Expr(Expr.Type type) {
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
