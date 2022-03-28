package parser.stmt;

import parser.expr.Expr;

public class ExprBlockStmt extends BlockStmt {
    private Expr expr;

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
