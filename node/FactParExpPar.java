package node;


/**
 * Fact Par Exp Par Node 
 */
public class FactParExpPar extends Fact{
    protected Expr expr;

    public FactParExpPar(Expr expr) {
        this.expr = expr;
    }
}
