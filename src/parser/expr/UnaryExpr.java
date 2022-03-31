package parser.expr;

public class UnaryExpr extends Expr {

    String op;  // operation
    Expr rhs;   // operand 2

    public UnaryExpr(Type type, String op, Expr rhs) {
        super(type);
        this.op = op;
        this.rhs = rhs;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Expr getRhs() {
        return rhs;
    }

    public void setRhs(Expr rhs) {
        this.rhs = rhs;
    }
}
