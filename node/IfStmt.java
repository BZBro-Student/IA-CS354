package node;

import eval.Environment;
import eval.EvalException;

public class IfStmt {
    BoolExpr boolExpr;
    ThenStmt thenStmt;
    Stmt stmt1;
    ElseStmt elseStmt;
    Stmt stmt2;

    public IfStmt(BoolExpr boolExpr, ThenStmt thenStmt, Stmt stmt1, ElseStmt elseStmt, Stmt stmt2) {
    this.boolExpr = boolExpr;
        this.thenStmt = thenStmt;
        this.stmt1 = stmt1;
        this.elseStmt = elseStmt;
        this.stmt2 = stmt2;
    }

    public IfStmt(BoolExpr boolExpr, ThenStmt thenStmt, Stmt stmt1) {
    this.boolExpr = boolExpr;
        this.thenStmt = thenStmt;
        this.stmt1 = stmt1;
        this.elseStmt = null;
        this.stmt2 = null;
    }
    public double eval(Environment env) throws EvalException {
        double condition = boolExpr.eval(env);
        if (condition != 0.0) {
            return stmt1.eval(env);
        } else if (stmt2 != null) {
            return stmt2.eval(env);
        }
        
        return 0.0;
    }
}

