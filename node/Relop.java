package node;

import eval.EvalException;
import syntax.Token;

public class Relop extends Node{
    protected Token relop;

    public Relop(int position, Token relop) {
        this.position = position;
        this.relop = relop;
    }

    public double compute(double op1, double op2) throws EvalException {
        String expression = relop.getLexeme();
        switch (expression) {
            case "<":
                return (op1 < op2) ? 1.0 : 0.0;
            case "<=":
                return (op1 <= op2) ? 1.0 : 0.0;
            case ">":
                return (op1 > op2) ? 1.0 : 0.0;
            case ">=":
                return (op1 >= op2) ? 1.0 : 0.0;
            case "<>":
                return (op1 != op2) ? 1.0 : 0.0;
            case "==":
                return (op1 == op2) ? 1.0 : 0.0;
            default:
                throw new EvalException(position, expression);
        }
    }
    
}
