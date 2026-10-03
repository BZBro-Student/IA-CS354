package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

public class Write extends Node{
    protected Expr expr;
    public Write(Expr expr) {
        this.expr = expr;
    } 

    @Override
    public double eval(Environment env) throws EvalException {
        double result = expr.eval(env);
        System.out.println(result);
        return result;
    }

}
