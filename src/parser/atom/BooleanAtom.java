package parser.atom;

import parser.DataType;

public class BooleanAtom extends Atom {
    public BooleanAtom(String value) {
        super(DataType.Boolean, value);
    }
}
