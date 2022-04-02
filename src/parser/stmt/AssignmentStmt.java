package parser.stmt;

import org.json.JSONObject;
import parser.expr.Expr;
import parser.var.Var;

public abstract class AssignmentStmt extends Stmt {
    private Expr expr;
    private Var var;

    public AssignmentStmt(Type type, Expr expr, Var var) {
        super(type);
        this.expr = expr;
        this.var = var;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("type", type);
        jsonObject.putOnce("var", var.toJsonObject());
        jsonObject.putOnce("expr", expr.toJsonObject()); // TODO: implement
        return jsonObject;
    }
}
