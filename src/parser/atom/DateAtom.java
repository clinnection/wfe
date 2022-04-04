package parser.atom;

import parser.DataType;

public class DateAtom extends Atom {
    public DateAtom(String value) {
        super(DataType.Date, value);
    }
}
