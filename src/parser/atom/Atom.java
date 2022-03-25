package parser.atom;

public class Atom {
    enum Type {
        Invalid,
        Boolean,
        Integer,
        Decimal,
        String,
        Identifier
    }

    public Type getType() {
        return type;
    }

    Type type = Type.Invalid;

}
