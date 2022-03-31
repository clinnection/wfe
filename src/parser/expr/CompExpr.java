package parser.expr;

public class CompExpr extends BinaryExpr {

    public CompExpr(String op, Expr lhs, Expr rhs) { super(Type.Comp, op, lhs, rhs); }
}
