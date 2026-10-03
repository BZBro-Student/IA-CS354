package node;

import eval.Environment;
import eval.EvalException;
import syntax.*;

/**
 * Addop Node is a Node representation of an Addop in a parse tree
 */
public class Addop extends Node {

    protected Token addop;

    public Addop(int position, Token addop) {

        this.position = position;
        this.addop = addop;
    }

    public double compute(double op1, double op2) throws EvalException {
        if ("-".equals(addop.getLexeme())) {
            return op1 - op2;
        } else {
            return op1 + op2;
        }
    }

}