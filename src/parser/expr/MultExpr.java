package parser.expr;

public class MultExpr extends BinaryExpr {
    public MultExpr(String op, Expr lhs, Expr rhs) {
        super(Type.Mult, op, lhs, rhs);
    }
}
