package parser;

import parser.stmt.Stmt;
import parser.var.Var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Block {

//    private List<Var> vars = new ArrayList<Var>();
    private HashMap<String, Var> vars = new HashMap<String, Var>();
    private List<Stmt> stmts = new ArrayList<Stmt>();

    public void addStmt(Stmt stmt) {
        stmts.add(stmt);
    }
    public Stmt getCurrentStmt() {
        return stmts.get(stmts.size() - 1);
    }

    public void addVar(Var var) { vars.put(var.getName(), var); }
    public HashMap<String, Var> getVars() { return this.vars; }
    public Var getVar(String name) { return this.vars.get(name); }

}
