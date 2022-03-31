package parser.stmt;

public class Stmt {

    enum Type {
        Invalid,
        If,
        ElseIf,
        Else,
        While,
        VarAssignment,
        Program
    };

    protected Stmt.Type type = Stmt.Type.Invalid;

    public Stmt(Type type) {
        this.type = type;
    }

    public Stmt.Type getType() {
        return type;
    }

    public void setType(Stmt.Type type) {
        this.type = type;
    }
}
