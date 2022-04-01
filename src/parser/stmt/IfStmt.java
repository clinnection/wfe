package parser.stmt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IfStmt extends ExprBlockStmt {

    public IfStmt() {
        super(Type.If);
    }

    private List<ElseIfStmt> elseIfStmts = new ArrayList<ElseIfStmt>();
    private ElseStmt elseStmt;

    public ElseStmt getElseStmt() {
        return elseStmt;
    }
    public void setElseStmt(ElseStmt elseStmt) {
        this.elseStmt = elseStmt;
    }

    public List<ElseIfStmt> getElseIfStmts() {
        return elseIfStmts;
    }
    public void addElseIfStmts(ElseIfStmt elseIfStmt) {
        this.elseIfStmts.add(elseIfStmt);
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = super.toJsonObject();

        if (elseStmt != null) {
            jsonObject.putOnce("else", elseStmt.toJsonObject());
        } else {
            jsonObject.putOnce("else", new JSONObject());
        }

        JSONArray jsonElseIfStmts = new JSONArray();
        elseIfStmts.forEach((value) -> jsonElseIfStmts.put(value.toJsonObject()));
        jsonObject.putOnce("elseIfStmts", jsonElseIfStmts);

        return jsonObject;
    }
}
