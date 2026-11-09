package node;

import eval.Environment;
import eval.EvalException;

public class Stmt extends Node {
    protected Assn assn;
    protected Read rd;
    protected Write wr;
    protected IfStmt ifStmt;
    protected WhileStmt whileStmt;
    protected Block block;

    public Stmt(Assn assn) {
        this.assn = assn;
    }

    public Stmt(Read rd) {
        this.rd = rd;
    }

    public Stmt(Write wr) {
        this.wr = wr;
    }

    public Stmt(IfStmt ifStmt) {
        this.ifStmt = ifStmt;
    }

    public Stmt(WhileStmt whileStmt) {
        this.whileStmt = whileStmt;
    }

    public Stmt(Block block) {
        this.block = block;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        if (assn != null) {
            return assn.eval(env);
        } else if (rd != null) {
            return rd.eval(env);
        } else if (wr != null) {
            return wr.eval(env);
        } else if (ifStmt != null) {
            return ifStmt.eval(env);
        } else if (whileStmt != null) {
            return whileStmt.eval(env);
        } else if (block != null) {
            return block.eval(env);
        } else {
            throw new EvalException(position,"Uninitialized statement node.");
        }
    }
}