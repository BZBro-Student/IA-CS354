package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

public class ThenStmt extends Node {
    protected Token token;

    public ThenStmt(int position, Token token) {
        this.position = position;
        this.token = token;
    }
}