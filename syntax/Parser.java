package syntax;

import node.*;

/**
 * A recursive descent parser
 */
public class Parser {

    private Lexer lexer;
    private Token lookahead;

    /**
     * Parse an input program and return a Node that is the root of the
     * resulting parse tree.
     *
     * @param program String to be scanned and parsed
     * @return the Root Node of a parse tree
     * @throws SyntaxException - If there is a syntax error
     */
    public Node parse(String program) throws SyntaxException {
        lexer = new Lexer(program);
        lookahead = lexer.next(); // "prime the pump"
        Block block = parseBlock();
        match("EOF");
        return block;
    }

    /**
     * @return Expr
     * @throws SyntaxException
     */
    private Expr parseExpr() throws SyntaxException {

        Term term = parseTerm();
        Addop addop = parseAddop();

        if (addop == null) {
            return new Expr(term);
        } else {
            Expr expr = parseExpr();
            expr.append(new Expr(term, addop, null));
            return expr;
        }
    }

    /**
     * @return Block
     * @throws SyntaxException
     */
    private Block parseBlock() throws SyntaxException {
        Stmt stmt = parseStmt();
        Token current = lookahead;

        if (current.equalType(new Token(";"))) {
            match(";");
            Block block = parseBlock();
            return new Block(stmt, block);
        } else {
            return new Block(stmt);
        }

    }

    /**
     * @return Stmt
     * @throws SyntaxException
     */
    private Stmt parseStmt() throws SyntaxException {
        Token current = lookahead;
        String lexeme = current.getLexeme();

        if (lexeme.equals("rd")) {
            match("rd");
            Fact factID = parseFact();
            return new Stmt(new Read(factID));

        } else if (lexeme.equals("wr")) {
            match("wr");
            Expr expr = parseExpr();
            Write wr = parseWrite(expr);
            return new Stmt(wr);

        } else if (lexeme.equals("if")) {
            match("if");
            BoolExpr boolExpr = parseBoolExpr();

            Token thenToken = lookahead;
            match("then");
            ThenStmt thenStmtMarker = new ThenStmt(lexer.getPosition(),thenToken);

            Stmt thenStmt = parseStmt();

            if (lookahead != null && lookahead.getLexeme().equals("else")) {
                Token elseToken = lookahead;
                match("else");
                ElseStmt elseStmtMarker = new ElseStmt(lexer.getPosition(),elseToken);

                Stmt elseStmt = parseStmt();
                return new Stmt(new IfStmt(boolExpr, thenStmtMarker, thenStmt, elseStmtMarker, elseStmt));
            } else {
                return new Stmt(new IfStmt(boolExpr, thenStmtMarker, thenStmt));
            }

        } else if (lexeme.equals("while")) {
            match("while");
            BoolExpr boolExpr = parseBoolExpr();

            Token doToken = lookahead;
            match("do");
            DoStmt doStmtMarker = new DoStmt(lexer.getPosition(), doToken);

            Stmt stmt = parseStmt();
            return new Stmt(new WhileStmt(boolExpr, doStmtMarker, stmt));

        } else if (lexeme.equals("begin")) {
            match("begin");
            Block block = parseBlock();
            match("end");
            return new Stmt(block);

        } else {
            Assn assn = parseAssn();
            if (assn != null) {
                return new Stmt(assn);
            } else {
                throw new SyntaxException(lexer.getPosition(), null, current);
            }
        }
    }

    private Write parseWrite(Expr expr) {
        return new Write(expr);
    }

    private Assn parseAssn() throws SyntaxException {
        Token current = lookahead;
        if (!current.equalType(new Token("id"))) {
            return null;
        }
        Fact fact = parseFact();
        current = lookahead;
        if (current.equalType(new Token("="))) {
            match("=");
            Expr expr = parseExpr();
            return new Assn(fact, expr);
        } else {
            throw new SyntaxException(lexer.getPosition(), new Token("="), current);
        }
    }

    /**
     * @return Term Node
     * @throws SyntaxException
     */
    private Term parseTerm() throws SyntaxException {

        Fact fact = parseFact();
        Mulop mulop = parseMulop();

        if (mulop == null) {
            return new Term(fact);
        } else {
            Term term = parseTerm();
            term.append(new Term(fact, mulop, null));
            return term;
        }

    }

    /**
     * @return Fact Node
     * @throws SyntaxException
     */
    private Fact parseFact() throws SyntaxException {

        Token current = lookahead;

        if (current.equalType(new Token("id"))) {
            match("id");
            return new FactID(current);
        } else if (current.equalType(new Token("num"))) {
            match("num");
            return new FactNum(current);
        } else if (current.equalType(new Token("("))) {
            match("(");
            Expr expr = parseExpr();
            match(")");
            return new FactParExpPar(expr);
        } else if (current.equalType(new Token("-"))) {
            match("-");
            return new NegFact(current, parseFact());

        } else {
            throw new SyntaxException(lexer.getPosition(), null, current);
        }
    }

    /**
     * Parses an Addop nonterminal and returns it.
     * 
     * @return a Node that represent an addop
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private Addop parseAddop() throws SyntaxException {
        Token addop = lookahead;
        if (addop.equalType("+")) {
            match("+");
            return new Addop(lexer.getPosition(), addop);

        } else if (addop.equalType("-")) {
            match("-");
            return new Addop(lexer.getPosition(), addop);

        } else {
            return null;
        }
    }

    /**
     * @return a Mulop Node
     * @throws SyntaxException
     */
    private Mulop parseMulop() throws SyntaxException {
        Token mulop = lookahead;
        if (mulop.equalType("*")) {
            match("*");
            return new Mulop(lexer.getPosition(), mulop);

        } else if (mulop.equalType("/")) {
            match("/");
            return new Mulop(lexer.getPosition(), mulop);

        } else {
            return null;
        }

    }

    private void match(String s) throws SyntaxException {
        if (lookahead.equalType(s)) {
            lookahead = lexer.next();
        } else {
            throw new SyntaxException(lexer.getPosition(), new Token(s), lookahead);
        }
    }

    /**
     * @return BoolExpr node
     * @throws SyntaxException
     */
    private BoolExpr parseBoolExpr() throws SyntaxException {
        Expr leftExpr = parseExpr();
        Relop relop = parseRelop();
        Expr rightExpr = parseExpr();
        return new BoolExpr(leftExpr,relop,rightExpr);
    }

    private Relop parseRelop() throws SyntaxException {
        Token relop = lookahead;
        String relopString = relop.getLexeme();

        if (relopString.equals("<") || relopString.equals("<=") || relopString.equals("<>") ||
            relopString.equals(">") || relopString.equals(">=") || relopString.equals("==")) {
                match(relopString);
                return new Relop(lexer.getPosition(),relop);
            } else {
                throw new SyntaxException(lexer.getPosition(), null, relop);
            }
    }
}