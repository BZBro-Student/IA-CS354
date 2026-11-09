package eval;

import node.*;
import syntax.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * System tests for the semantic actions of the programming language.
 */
class EvaluationTest {

    private Parser parser;
    private Environment env;

    @BeforeEach
    void setUp() {

        parser = new Parser();
        env = new Environment();
    }

    @Test
    void testAssignment() throws SyntaxException, EvalException {
        String prg = "x = 1; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testAssignmentAdd() throws SyntaxException, EvalException {
        String prg = "x = 2; y = 4; wr x + y";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(6, evaluation);
    }

    @Test
    void testAssignmentSub() throws SyntaxException, EvalException {
        String prg = "x = 2; y = 4; wr x - y";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(-2, evaluation);
    }

    @Test
    void testAssignmentMul() throws SyntaxException, EvalException {
        String prg = "x = 2; y = 4; wr x * y";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(8, evaluation);
    }

    @Test
    void testAssignmentMulNeg() throws SyntaxException, EvalException {
        String prg = "x = 2; y = 4; wr x * -y";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(-8, evaluation);
    }

    @Test
    void testEvalError() throws SyntaxException {

        String prg = "wr x";
        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testAssociation() throws SyntaxException, EvalException {
        String prg = "wr 10 - 4 - 3";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testPrecedenceDiv() throws SyntaxException, EvalException {
        String prg = "wr 6 / (10 - 8)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testPrecedenceSub() throws SyntaxException, EvalException {
        String prg = "wr 10 - (4 - 3)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(9, evaluation);
    }

    @Test
    void testRelationalEqual() throws SyntaxException, EvalException {
        String prg = "x = 1; if x == 1 then wr 1 else wr 0";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testRelationalNotEqual() throws SyntaxException, EvalException {
        String prg = "x = 2; if x <> 1 then wr 1 else wr 0";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testRelationalLessOrEqual() throws SyntaxException, EvalException {
        String prg = "x = 1; if x <= 1 then wr 1 else wr 0";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testWhileLoop() throws SyntaxException, EvalException {
        String prg = "x = 3; begin while x <> 0 do begin x = x - 1 end end; wr x";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    void testIfElseBranching() throws SyntaxException, EvalException {
        String prg = "x = 1; if x > 5 then wr 1 else wr 2";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation);
    }

    @Test
    void testRd() throws SyntaxException, EvalException {
        String input = "4.1\n4.2";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd x; rd y; wr y + x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(8.3, evaluation);
    }

    @Test
    void testGCD() throws SyntaxException, EvalException {
        String input = "4\n8";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd a; rd b; while a<>b do if a > b then a = a-b else b = b-a; wr a";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(4, evaluation);
    }

    @Test 
    void testFib() throws SyntaxException, EvalException {
        String input = "6";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd n; if n <= 2 then wr 1 else begin a = 1; b = 1; i = 3; while i <= n do begin temp = a; a = a + b; b = temp; i = i +1 end; wr a end";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(8, evaluation);
    }

    @Test 
    void testDotProduct() throws SyntaxException, EvalException {
        String input = "6\n2\n5\n8";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd x1; rd y1; rd x2; rd y2; dot = (x1 * x2) + (y1 * y2); wr dot";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(46, evaluation);
    }

    @Test 
    void testFactorial() throws SyntaxException, EvalException {
        String input = "10";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd n; factorial = 1; while n > 0 do begin factorial = factorial * n; n = n - 1 end; wr factorial";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3628800, evaluation);
    }

}
