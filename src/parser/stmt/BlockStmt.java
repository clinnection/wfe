package parser.stmt;

import parser.Block;

public class BlockStmt extends Stmt{
    private Block block;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
