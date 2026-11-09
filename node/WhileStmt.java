package node;

import eval.Environment;
import eval.EvalException;

public class WhileStmt extends Node {
    protected BoolExpr boolexpr;
    protected DoStmt dostmt;
    protected Stmt stmt;

    public WhileStmt(BoolExpr boolexpr, DoStmt dostmt, Stmt stmt) {
        this.boolexpr = boolexpr;
        this.dostmt = dostmt;
        this.stmt = stmt;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        double result = 0.0;

        while (boolexpr.eval(env) != 0.0) {
            result = stmt.eval(env);
        }
        return result;
    }
}