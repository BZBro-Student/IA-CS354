package node;

import eval.EvalException;
import syntax.Token;

/**
 * Mulop Node 
 */
public class Mulop extends Node {
    protected Token mulop;

    public Mulop(int position, Token mulop) {

        this.position = position;
        this.mulop = mulop;
    }

    public double compute(double op1, double op2) throws EvalException {
        if ("/".equals(mulop.getLexeme())) {
            return op1 / op2;
        } else {
            return op1 * op2;
        }
    }
}
