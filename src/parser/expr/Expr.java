package parser.expr;

public class Expr {

    enum Type {
        Invalid,
        Add,
        Atom,
        Comp,
        Logic,
        Mult,
        Neg,
        Not,
        Paren
    };

    protected Expr.Type type = Expr.Type.Invalid;

    public Expr(Expr.Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
