package parser.expr;

public class BinaryExpr extends Expr {

    String op;  // operation

    Expr lhs;   // operand 1
    Expr rhs;   // operand 2

    public BinaryExpr(Type type, String op, Expr lhs, Expr rhs) {
        super(type);
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Expr getLhs() {
        return lhs;
    }

    public void setLhs(Expr lhs) {
        this.lhs = lhs;
    }

    public Expr getRhs() {
        return rhs;
    }

    public void setRhs(Expr rhs) {
        this.rhs = rhs;
    }

    public String getValue() {
        return "(" + op + " " + lhs.getValue() + " " + rhs.getValue() + ")";
    }
}
