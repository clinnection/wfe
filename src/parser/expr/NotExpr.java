package parser.expr;

public class NotExpr extends UnaryExpr {
    public NotExpr( Expr rhs) {
        super("!", rhs);
    }
}
