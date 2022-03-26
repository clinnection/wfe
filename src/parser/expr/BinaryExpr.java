package parser.expr;

public class BinaryExpr extends Expr {
    Expr op1;
    Expr op2;

    public BinaryExpr(Expr op1, Expr op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    public Expr getOp1() {
        return op1;
    }

    public void setOp1(Expr op1) {
        this.op1 = op1;
    }

    public Expr getOp2() {
        return op2;
    }

    public void setOp2(Expr op2) {
        this.op2 = op2;
    }
}
