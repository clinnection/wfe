package parser.atom;

import parser.DataType;

public class StringAtom extends Atom {
    public StringAtom(String value) {
        super(DataType.String, value);
    }
}
