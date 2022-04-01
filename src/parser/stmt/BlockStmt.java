package parser.stmt;

import org.json.JSONObject;
import parser.Block;

public abstract class BlockStmt extends Stmt {
    private Block block;

    public BlockStmt(Type type) {
        super(type);
    }
    public void setBlock(Block block) {
        this.block = block;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.putOnce("block", block.toJsonObject());
        return jsonObject;
    }
}
