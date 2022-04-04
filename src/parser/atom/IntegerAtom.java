package parser.atom;

import parser.DataType;

public class IntegerAtom extends Atom {
    public IntegerAtom(String value) {
        super(DataType.Integer, value);
    }
}
