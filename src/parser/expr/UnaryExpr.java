package parser.expr;

public class UnaryExpr extends Expr {
    private Expr op;

    public Expr getOp() {
        return op;
    }

    public void setOp(Expr op) {
        this.op = op;
    }
}
