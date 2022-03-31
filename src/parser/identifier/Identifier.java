package parser.identifier;

public class Identifier {

    enum Type {
        Invalid,
        Var,
        Json
    };

    protected Type type = Type.Invalid;

    public Identifier(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
