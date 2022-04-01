package parser;

import org.json.JSONArray;
import org.json.JSONObject;
import parser.stmt.Stmt;
import parser.var.Var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Block {

    private HashMap<String, Var> vars = new HashMap<String, Var>();
    private List<Stmt> stmts = new ArrayList<Stmt>();

    public void addStmt(Stmt stmt) {
        stmts.add(stmt);
    }

    public void addVar(Var var) { vars.put(var.getName(), var); }
    public HashMap<String, Var> getVars() { return this.vars; }
    public Var getVar(String name) { return this.vars.get(name); }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonVars = new JSONArray();
        vars.forEach((key, value) -> jsonVars.put(value.toJsonObject()));
        jsonObject.putOnce("vars", jsonVars);

        JSONArray jsonStmts = new JSONArray();
        stmts.forEach((value) -> jsonStmts.put(value.toJsonObject()));
        jsonObject.putOnce("stmts", jsonStmts);

        return jsonObject;
    }

}
