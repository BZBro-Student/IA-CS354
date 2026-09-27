package node;

import syntax.Token;

/**
 * Fact Num Node 
 */
public class FactNum extends Fact {
    protected Token num;
    public FactNum(Token token) {
        this.num = token;
    }
    
}
