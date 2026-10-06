package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * Fact ID Node 
 */
public class FactID extends Fact{
    protected Token id;
    public FactID(Token token) {
        this.id = token;
    }
    @Override 
    public String getLexeme() {
        return id.getLexeme();
    }

    @Override
    public double eval(Environment env) throws EvalException {
        return env.get(this.position, id.getLexeme());
    }

}
