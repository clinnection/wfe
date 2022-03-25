import org.antlr.v4.runtime.ParserRuleContext;
import parser.Block;
import parser.Program;
import parser.atom.IntegerAtom;
import parser.stmt.BlockStmt;
import parser.stmt.IfStmt;
import parser.stmt.Stmt;

import java.util.List;
import java.util.Stack;

public class wfBuilder extends wfBaseListener {

    private Program program;
    private Stack<Block> blocks;
    private Stack<Stmt> stmts;

    public wfBuilder() {
        program = new Program();
        blocks = new Stack<Block>();
        stmts = new Stack<Stmt>();
    }

    @Override
    public void exitProgram(wfParser.ProgramContext ctx) {
        super.exitProgram(ctx);
        System.out.println("exitProgram " + ctx.getText().toString());
        program.setBlock(blocks.pop());
    }

    @Override
    public void enterBlock(wfParser.BlockContext ctx) {
        super.enterBlock(ctx);
        System.out.println("enterBlock " + ctx.getText().toString());
        Block block = new Block();
        blocks.push(block);
    }


    /*
     * If statements
     */
    @Override
    public void enterIfStmt(wfParser.IfStmtContext ctx) {
        super.enterIfStmt(ctx);
        stmts.push(new IfStmt());
    }

    @Override
    public void exitIfStmt(wfParser.IfStmtContext ctx) {
        super.exitIfStmt(ctx);
        System.out.println("exitIfStmt " + ctx.getText().toString());
        BlockStmt ifStmt = (BlockStmt) stmts.pop();
        ifStmt.setBlock(blocks.pop());
        blocks.peek().addStmt(ifStmt);
    }

    @Override
    public void enterElseifStmt(wfParser.ElseifStmtContext ctx) {
        super.enterElseifStmt(ctx);
        stmts.push(new IfStmt());
    }

    @Override
    public void exitElseifStmt(wfParser.ElseifStmtContext ctx) {
        super.exitElseifStmt(ctx);
        System.out.println("exitElseifStmt  " + ctx.getText().toString());
        BlockStmt elseIfStmt = (BlockStmt) stmts.pop();
        elseIfStmt.setBlock(blocks.pop());
        blocks.peek().addStmt(elseIfStmt);
    }
}
