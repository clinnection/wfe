package parser.expr;

public class LogicExpr extends BinaryExpr {
    public LogicExpr(String op, Expr lhs, Expr rhs) {
        super(Type.Logic, op, lhs, rhs);
    }
}
