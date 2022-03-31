package parser.var;

public class Var {

    enum Type {
        Invalid,
        Boolean,
        Integer,
        Decimal,
        String
    };

    protected Type type = Type.Invalid;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(Type type, String name) {
        this.name = name;
    }

    public Var(Type type, String name) {
        this.type = type;
        this.name = name;
    }
}
