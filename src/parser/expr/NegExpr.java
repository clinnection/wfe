package parser.expr;

public class NegExpr extends UnaryExpr {
    public NegExpr(Expr rhs) {
        super("-", rhs);
    }
}
