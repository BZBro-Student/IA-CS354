package node;

import syntax.Token;

public class End extends Node {
    protected Token token;

    public End(int position, Token token) {
        this.position = position;
        this.token = token;
    }
}