package node;

import eval.Environment;
import eval.EvalException;

public class BoolExpr extends Node{
    protected Expr expr1;
    protected Relop relop;
    protected Expr expr2;

    public BoolExpr(Expr expr1, Relop relop, Expr expr2 ) {
        this.expr1 = expr1;
        this.relop = relop;
        this.expr2 = expr2;
    }

    public double eval(Environment env) throws EvalException{
        double value = expr1.eval(env);
        if (relop != null) {
            return relop.compute(value, expr2.eval(env));
        }
        return value;
    }
    
}
