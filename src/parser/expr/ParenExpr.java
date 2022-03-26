package parser.expr;

public class ParenExpr extends UnaryExpr {
    public ParenExpr(Expr rhs) {
        super("(", rhs);
    }
}
