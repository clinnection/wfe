package parser;

import parser.stmt.Stmt;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private List<Stmt> stmts = new ArrayList<Stmt>();

    public void addStmt(Stmt stmt) {
        stmts.add(stmt);
    }

    public Stmt getCurrentStmt() {
        return stmts.get(stmts.size() - 1);
    }
}
