package node;

import eval.Environment;
import eval.EvalException;

public class Stmt extends Node {
    protected Assn assn;
    protected Write wr;

    public Stmt(Assn assn) {
        this.assn = assn;
    }

    public Stmt(Write wr) {
        this.wr = wr;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        double value;
        if (assn != null) {
            value = assn.eval(env);
        } else {
            value = wr.eval(env);
        }
        return value;
    }

}
