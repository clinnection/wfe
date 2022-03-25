package parser.atom;

import static java.lang.Integer.parseInt;

public class IntegerAtom extends Atom {
    public IntegerAtom(String text) {
        super();
        this.value = parseInt(text);
    }

    public int getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = parseInt(value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;
    private final Type type = Type.Integer;

    @Override
    public String toString() {
        return "IntegerAtom{" + "value=" + value + '}';
    }
}
