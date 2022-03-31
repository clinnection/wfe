package parser.atom;

import static java.lang.Integer.parseInt;

public class IntegerAtom extends Atom {

    public IntegerAtom(String value) {
        super(Type.Integer, value);
    }
}
