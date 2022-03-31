package parser.atom;

public class Atom {
    private String value;

    enum Type {
        Invalid,
        Boolean,
        Integer,
        Decimal,
        String,
        Var
    };

    protected Atom.Type type = Atom.Type.Invalid;

    public Atom(Atom.Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Atom(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
