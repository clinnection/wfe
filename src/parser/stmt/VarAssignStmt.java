package parser.stmt;

import parser.expr.Expr;
import parser.var.Var;

public class VarAssignStmt extends AssignmentStmt {

    public VarAssignStmt(Expr expr, Var var) {
        super(expr, var);
    }
}
