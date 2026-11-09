package syntax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * To assist you in creating correct parse trees.
 */
class ParserTest {

    @Test
    void testAssignment() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x = x";

        System.out.println(parser.parse(prg).toString());
    }

    @Test 
    void testNegativeNumber() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x = -3";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testAddition() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "wr x + 3";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testSubtraction() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "wr 10-4-3";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testComplexEquation() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x = (x+2) / 4; wr x";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testParenthesis() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "wr ((x))";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testSyntax_1() {

        Parser parser = new Parser();
        String prg = "x * *";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    @Test
    void testSyntax_2() {

        Parser parser = new Parser();
        String prg = "((x)";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    @Test
    void testSyntax_3() {

        Parser parser = new Parser();
        String prg = "x+ -3";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    @Test
    void testSyntax_4() {

        Parser parser = new Parser();
        String prg = "x";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    @Test
    void testReadAndWrite() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "rd x; wr x + 1";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testIfElseStatement() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "if x == 1 then wr 1 else wr 0";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testWhileLoopAndBlock() throws SyntaxException {
        Parser parser = new Parser();
        // Tests begin/end blocks, while loops, and self-assignment decrement
        String prg = "begin rd x; while x <> 0 do begin wr x; x = x - 1 end end";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testRelationalOperators() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "if x <= 10 then if y <> 0 then wr x else wr 0 else wr 1";

        System.out.println(parser.parse(prg).toString());
    }
}