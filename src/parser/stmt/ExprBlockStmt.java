package parser.stmt;

import org.json.JSONObject;
import parser.expr.Expr;

public abstract class ExprBlockStmt extends BlockStmt {
    private Expr expr;

    public ExprBlockStmt(Type type) {
        super(type);
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.putOnce("expr", expr.toJsonObject());
        return jsonObject;
    }
}
