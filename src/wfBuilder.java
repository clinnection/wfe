import parser.Block;
import parser.Program;
import parser.atom.*;
import parser.expr.*;
import parser.stmt.Stmt;

import java.util.Stack;

public class wfBuilder extends wfBaseListener {

    private Program program;
    private Stack<Block> blocks;
    private Stack<Stmt> stmts;
    private Stack<Expr> exprs;
    private Stack<Atom> atoms;

    public wfBuilder() {
        program = new Program();
        blocks = new Stack<Block>();
        stmts = new Stack<Stmt>();
        exprs = new Stack<Expr>();
        atoms = new Stack<Atom>();
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
    public void exitIfStmt(wfParser.IfStmtContext ctx) {
        super.exitIfStmt(ctx);
        System.out.println("exitIfStmt " + ctx.getText().toString());

    }

    @Override
    public void exitElseifStmt(wfParser.ElseifStmtContext ctx) {
        super.exitElseifStmt(ctx);
        System.out.println("exitElseifStmt " + ctx.getText().toString());
    }

    @Override
    public void exitElseStmt(wfParser.ElseStmtContext ctx) {
        super.exitElseStmt(ctx);
        System.out.println("exitElseStmt " + ctx.getText().toString());
    }

    /*
     * Binary Expressions
     */

    @Override
    public void exitCompExpr(wfParser.CompExprContext ctx) {
        super.exitCompExpr(ctx);
        System.out.println("exitCompExpr " + ctx.getText().toString());

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        CompExpr compExpr = new CompExpr(ctx.op.getText(), lhs, rhs);
        System.out.println("CompExpr: " + compExpr.getValue());

        exprs.push(compExpr);
    }

    @Override
    public void exitMultExpr(wfParser.MultExprContext ctx) {
        super.exitMultExpr(ctx);
        System.out.println("exitMultExpr " + ctx.getText().toString());

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        MultExpr multExpr = new MultExpr(ctx.op.getText(), lhs, rhs);
        System.out.println("MultExpr: " + multExpr.getValue());

        exprs.push(multExpr);
    }

    @Override
    public void exitAddExpr(wfParser.AddExprContext ctx) {
        super.exitAddExpr(ctx);
        System.out.println("exitAddExpr " + ctx.getText().toString());

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        AddExpr addExpr = new AddExpr(ctx.op.getText(), lhs, rhs);
        System.out.println("AddExpr: " + addExpr.getValue());

        exprs.push(addExpr);
    }

    @Override
    public void exitLogicalExpr(wfParser.LogicalExprContext ctx) {
        super.exitLogicalExpr(ctx);
        System.out.println("exitLogicalExpr " + ctx.getText().toString());

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        LogicExpr logicExpr = new LogicExpr(ctx.op.getText(), lhs, rhs);
        System.out.println("LogicExpr: " + logicExpr.getValue());

        exprs.push(logicExpr);
    }

    /*
     * Unary expressions
     */

    @Override
    public void exitNegExpr(wfParser.NegExprContext ctx) {
        super.exitNegExpr(ctx);
        System.out.println("exitNegExpr " + ctx.getText().toString());

        Expr rhs = exprs.pop();

        NegExpr negExpr = new NegExpr( rhs);
        System.out.println("NegExpr: " + negExpr.getValue());

        exprs.push(negExpr);
    }

    @Override
    public void exitNotExpr(wfParser.NotExprContext ctx) {
        super.exitNotExpr(ctx);
        System.out.println("exitNotExpr " + ctx.getText().toString());

        Expr rhs = exprs.pop();

        NotExpr notExpr = new NotExpr( rhs);
        System.out.println("NotExpr: " + notExpr.getValue());

        exprs.push(notExpr);
    }

    /*
     * Atom expression
     */
    @Override
    public void exitAtomExpr(wfParser.AtomExprContext ctx) {
        super.exitAtomExpr(ctx);
        System.out.println("exitAtomExpr " + ctx.getText().toString());
        exprs.push(new AtomExpr(atoms.pop()));
    }

    /*
     * Atoms
     */

    @Override
    public void enterIntAtom(wfParser.IntAtomContext ctx) {
        super.enterIntAtom(ctx);
        System.out.println("enterIntAtom " + ctx.getText().toString());
        atoms.push(new IntAtom(ctx.getText().toString()));
    }

    @Override
    public void enterVarAtom(wfParser.VarAtomContext ctx) {
        super.enterVarAtom(ctx);
        System.out.println("enterVarAtom " + ctx.getText().toString());
        atoms.push(new VarAtom(ctx.getText().toString()));
    }

    @Override
    public void enterBoolAtom(wfParser.BoolAtomContext ctx) {
        super.enterBoolAtom(ctx);
        System.out.println("enterBoolAtom " + ctx.getText().toString());
        atoms.push(new BoolAtom(ctx.getText().toString()));
    }

    @Override
    public void enterStrAtom(wfParser.StrAtomContext ctx) {
        super.enterStrAtom(ctx);
        System.out.println("enterStrAtom " + ctx.getText().toString());
        atoms.push(new StrAtom(ctx.getText().toString()));
    }

    @Override
    public void enterNumAtom(wfParser.NumAtomContext ctx) {
        super.enterNumAtom(ctx);
        System.out.println("enterNumAtom " + ctx.getText().toString());
        atoms.push(new NumAtom(ctx.getText().toString()));
    }
}
