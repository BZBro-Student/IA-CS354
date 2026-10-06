package node;

import eval.Environment;
import eval.EvalException;

public class Assn extends Node {
    protected Fact id;
    protected Expr expr;

    public Assn(Fact id, Expr expr){
        this.id = id;
        this.expr = expr;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        double value = expr.eval(env);
        env.put(id.getLexeme(),value);
        return value;
    }

}
