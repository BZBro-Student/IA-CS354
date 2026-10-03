package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * Fact Num Node 
 */
public class FactNum extends Fact {
    protected Token num;
    public FactNum(Token token) {
        this.num = token;
    }
    
    @Override
    public double eval(Environment env) throws EvalException {
        return env.get(this.position, num.getLexeme());
    }
}
