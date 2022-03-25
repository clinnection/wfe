package parser.stmt;

public class Stmt {
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private Type type = Type.Invalid;
    enum Type {
        Invalid,
        If,
        ElseIf,
        Else,
        While,
        Assignment
    };
}
