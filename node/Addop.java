package node;

import syntax.*;

/**
 * Addop Node is a Node representation of an Addop in a parse tree
 */
public class Addop extends Node {

    protected Token addop;

    public Addop(int position, Token addop) {

        this.position = position;
        this.addop = addop;
    }
}