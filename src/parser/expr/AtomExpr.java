package parser.expr;

import parser.atom.Atom;

public class AtomExpr extends Expr {
    Atom atom;

    public AtomExpr(Atom atom) {
        super(Type.Atom);
        this.atom = atom;
    }

    public Atom getAtom() {
        return atom;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    public String getValue() {
        return atom.getValue();
    }
}
