package parser.expr;

import org.json.JSONObject;
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

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("type", type);
        jsonObject.putOnce("atom", atom.toJsonObject());
        return jsonObject;
    }

}