package parser.atom;

import parser.DataType;

public class DecimalAtom extends Atom {
    public DecimalAtom(String value) {
        super(DataType.Decimal, value);
    }

}
