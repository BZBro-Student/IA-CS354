package node;

import syntax.Token;

public class ElseStmt extends Node {
    protected Token token;

    public ElseStmt(int position, Token token) {
        this.position = position;
        this.token = token;
    }
}