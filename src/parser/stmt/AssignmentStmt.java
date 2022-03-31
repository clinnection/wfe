package parser.stmt;

import parser.expr.Expr;
import parser.var.Var;

public class AssignmentStmt extends Stmt {
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
}
