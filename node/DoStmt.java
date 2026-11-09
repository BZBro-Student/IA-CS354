package node;

import syntax.Token;

public class DoStmt extends Node {
    protected Token token;

    public DoStmt(int position, Token token) {
        this.position = position;
        this.token = token;
    }
}