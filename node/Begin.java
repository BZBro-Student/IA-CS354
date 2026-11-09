package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

public class Begin extends Node {
    protected Token token;

    public Begin(int position, Token token) {
        this.position = position;
        this.token = token;
    }
}