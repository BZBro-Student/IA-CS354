package node;

import eval.Environment;
import eval.EvalException;

public class Block extends Node {
    protected Stmt stmt;
    protected Block block;

    public Block(Stmt stmt, Block block) {
        this.stmt = stmt;
        this.block = block;
    }

    public Block(Stmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        double value = stmt.eval(env);
        if (block != null) {
            return block.eval(env);
        }

        return value;
    }

}
