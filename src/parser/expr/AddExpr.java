package parser.expr;

public class AddExpr extends BinaryExpr {
    public AddExpr(String op, Expr lhs, Expr rhs) {
        super(Type.Add, op, lhs, rhs);
    }
}
