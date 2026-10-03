package node;
import eval.Environment;
import eval.EvalException;
import syntax.Token;

public class NegFact extends Fact {
    protected Token neg;
    protected Fact fact;

    public NegFact(Token neg, Fact fact) {
        this.neg = neg;
        this.fact = fact;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        return -fact.eval(env);
    }
    
}
