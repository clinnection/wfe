import parser.Block;
import parser.atom.*;
import parser.expr.*;
import parser.stmt.*;
import parser.var.*;

import java.util.Stack;

public class wfBuilder extends wfBaseListener {

    private Stack<Block> blocks;
    private Stack<Stmt>  stmts;
    private Stack<Expr>  exprs;
    private Stack<Atom>  atoms;

    public wfBuilder() {
        blocks  = new Stack<Block>();
        stmts   = new Stack<Stmt>();
        exprs   = new Stack<Expr>();
        atoms   = new Stack<Atom>();
    }

    @Override
    public void enterProgram(wfParser.ProgramContext ctx) {
        super.enterProgram(ctx);
        System.out.println("enterProgram " + ctx.getText().toString());
        stmts.push(new ProgramStmt());
    }

    @Override
    public void exitProgram(wfParser.ProgramContext ctx) {
        super.exitProgram(ctx);
        System.out.println("exitProgram " + ctx.getText().toString());
    }


    /*
     * Block
     */
    @Override
    public void enterBlock(wfParser.BlockContext ctx) {
        super.enterBlock(ctx);
        System.out.println("enterBlock " + ctx.getText().toString());

        Block block = new Block();
        blocks.push(block);
    }

    @Override
    public void exitBlock(wfParser.BlockContext ctx) {
        super.exitBlock(ctx);
        System.out.println("exitBlock " + ctx.getText().toString());

        BlockStmt s = (BlockStmt) stmts.peek();
        s.setBlock(blocks.pop());
    }

    /*
     * Declarations
     */

    @Override
    public void exitDecl(wfParser.DeclContext ctx) {
        super.exitDecl(ctx);


        String name = ctx.decl_var.getText().toString();

        System.out.println("exitDecl " + ctx.getText().toString());
        System.out.println("   type: " + ctx.decl_type.getText().toString());
        System.out.println("    var: " + name);

        Var var;

        if (blocks.peek().getVars().containsKey((name))) {
            throw new RuntimeException(name + ": already declared");
        }

        switch (ctx.decl_type.getText().toString()) {
            case "string":
                var = new StringVar(name);
                break;
            case "integer":
                var = new IntegerVar(name);
                break;
            case "boolean":
                var = new BooleanVar(name);
                break;
            case "decimal":
                var = new DecimalVar(name);
                break;
            default:
                throw new RuntimeException(name + ": invalid data type");
        }

        blocks.peek().addVar(var);
    }

    /*
     * Assignment
     */

    @Override
    public void enterVarAssignStmt(wfParser.VarAssignStmtContext ctx) {
        super.enterVarAssignStmt(ctx);
        System.out.println("enterVarAssignStmt " + ctx.getText().toString());
    }

    @Override
    public void exitVarAssignStmt(wfParser.VarAssignStmtContext ctx) {
        super.exitVarAssignStmt(ctx);
        System.out.println("exitVarAssignStmt " + ctx.getText().toString());

        String name = ctx.VAR_IDENTIFIER().getText().toString();

        Var v = null;
        for (int i = blocks.size() - 1; i >= 0; i--) {
            v = blocks.get(i).getVars().get(name);
            if (v != null) {
                break;
            }
        }

        if (v == null) {
            throw new RuntimeException(name + ": not found");
        }

        blocks.peek().addStmt(new VarAssignStmt(exprs.pop(), v));
    }

    /*
     * IfStmt statement
     */
    @Override
    public void enterIfStmt(wfParser.IfStmtContext ctx) {
        super.enterIfStmt(ctx);
        System.out.println("enterIfStmt " + ctx.getText().toString());
        stmts.push(new IfStmt());
    }

    @Override
    public void exitIfStmt(wfParser.IfStmtContext ctx) {
        super.exitIfStmt(ctx);
        System.out.println("exitIfStmt " + ctx.getText().toString());

        Stmt ifStmt = stmts.pop();
        Block block = blocks.peek();
        block.addStmt(ifStmt);
    }


    /*
     * ElseIfStmt statement
     */
    @Override
    public void enterElseIfStmt(wfParser.ElseIfStmtContext ctx) {
        super.enterElseIfStmt(ctx);
        System.out.println("enterElseIfStmt " + ctx.getText().toString());
        stmts.push(new ElseIfStmt());
    }

    @Override
    public void exitElseIfStmt(wfParser.ElseIfStmtContext ctx) {
        super.exitElseIfStmt(ctx);
        System.out.println("exitElseIfStmt " + ctx.getText().toString());

        ElseIfStmt elseIfStmt = (ElseIfStmt) stmts.pop();
        IfStmt ifStmt = (IfStmt) stmts.peek();
        ifStmt.addElseIfStmts(elseIfStmt);
    }


    /*
     * ElseStmt statement
     */
    @Override
    public void enterElseStmt(wfParser.ElseStmtContext ctx) {
        super.enterElseStmt(ctx);
        System.out.println("exitElseStmt " + ctx.getText().toString());
        stmts.push(new ElseStmt());
    }

    @Override
    public void exitElseStmt(wfParser.ElseStmtContext ctx) {
        super.exitElseStmt(ctx);
        System.out.println("exitElseStmt " + ctx.getText().toString());

        ElseStmt elseStmt = (ElseStmt) stmts.pop();
        IfStmt ifStmt = (IfStmt) stmts.peek();
        ifStmt.setElseStmt(elseStmt);
    }


    /*
     * Then
     */
    @Override
    public void exitIfThen(wfParser.IfThenContext ctx) {
        super.exitIfThen(ctx);
        System.out.println("exitIfThen " + ctx.getText().toString());

        ExprBlockStmt s = (ExprBlockStmt) stmts.peek();
        s.setExpr(exprs.pop());
    }

    /*
     * While
     */
    @Override
    public void enterWhileStmt(wfParser.WhileStmtContext ctx) {
        super.enterWhileStmt(ctx);
        System.out.println("enterWhileStmt " + ctx.getText().toString());
        stmts.push(new WhileStmt());
    }

    @Override
    public void exitWhileStmt(wfParser.WhileStmtContext ctx) {
        super.exitWhileStmt(ctx);
        System.out.println("exitWhileStmt " + ctx.getText().toString());

        Stmt whileStmt = stmts.pop();
        Block block = blocks.peek();
        block.addStmt(whileStmt);
    }

    // TODO: add continue
    // TODO: add break

    /*
     * Do
     */
    @Override
    public void exitWhileDo(wfParser.WhileDoContext ctx) {
        super.exitWhileDo(ctx);
        System.out.println("exitWhileDo " + ctx.getText().toString());

        ExprBlockStmt s = (ExprBlockStmt) stmts.peek();
        s.setExpr(exprs.pop());
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

    @Override
    public void exitParenExpr(wfParser.ParenExprContext ctx) {
        super.exitParenExpr(ctx);
        System.out.println("exitParenExpr " + ctx.getText().toString());

        Expr rhs = exprs.pop();

        ParenExpr parenExpr = new ParenExpr( rhs);
        System.out.println("ParenExpr: " + parenExpr.getValue());

        exprs.push(parenExpr);
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

    @Override
    public void enterVarAtom(wfParser.VarAtomContext ctx) {
        super.enterVarAtom(ctx);

        String name = ctx.getText().toString();
        System.out.println("enterVarAtom " + name);

        if (!varExists(ctx.getText().toString())) {
            System.err.println(name + ": not found");
        }

        atoms.push(new VarAtom(ctx.getText().toString()));
    }

    private boolean varExists(String name) {
        for (int i = blocks.size() - 1; i >= 0; i--) {
            if (blocks.get(i).getVars().containsKey(name)) {
                return true;
            }
        }
        return false;
    }
}
