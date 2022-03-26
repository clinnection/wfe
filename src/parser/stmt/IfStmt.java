package parser.stmt;

import parser.Block;
import parser.expr.Expr;

import java.util.List;

public class IfStmt extends BlockStmt {
    private Expr expr;
    private Block block;
    private List<ElseIfStmt> elseIfStmts;
    private ElseStmt elseStmt;
}
