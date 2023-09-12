package nl.bigo.elixirp;

import org.junit.Test;

import static nl.bigo.elixirp.ElixirLexer.*;

public class LexerTests extends BaseTest {

    @Test
    public void atomTest() {
        String[] tests = {
                ":foo",
                ":ME@mu?",
                ":*",
                ":/",
                ":+",
                ":-",
                ":'fu-bar'",
                ":\"fu-bar\"",
                ":げごさざ!",
                ":\"a\nb\nc\""
        };

        super.testTokens(tests, ATOM);
    }

    @Test
    public void trueTest() {
        super.testSingleToken("true", TRUE);
    }

    @Test
    public void falseTest() {
        super.testSingleToken("false", FALSE);
    }

    @Test
    public void nilTest() {
        super.testSingleToken("nil", NIL);
    }

    @Test
    public void whenTest() {
        super.testSingleToken("when", WHEN);
    }

    @Test
    public void andTest() {
        super.testSingleToken("and", AND);
    }

    @Test
    public void orTest() {
        super.testSingleToken("or", OR);
    }

    @Test
    public void notTest() {
        super.testSingleToken("not", NOT);
    }

    @Test
    public void inTest() {
        super.testSingleToken("in", IN);
    }

    @Test
    public void fnTest() {
        super.testSingleToken("fn", FN);
    }

    @Test
    public void doTest() {
        super.testSingleToken("do", DO);
    }

    @Test
    public void endTest() {
        super.testSingleToken("end", END);
    }

    @Test
    public void catchTest() {
        super.testSingleToken("catch", CATCH);
    }

    @Test
    public void rescueTest() {
        super.testSingleToken("rescue", RECUE);
    }

    @Test
    public void afterTest() {
        super.testSingleToken("after", AFTER);
    }

    @Test
    public void elseTest() {
        super.testSingleToken("else", ELSE);
    }

    @Test
    public void integerTest() {
        String[] tests = {
                "0",
                "42",
                "1234535616542563465214365124365124361254315624",
                "1_2_3_4_5",
                "1_000_000"
        };

        super.testTokens(tests, INTEGER);
    }

    @Test
    public void floatTest() {
        String[] tests = {
                "0.1",
                "42.2345678",
                "10.1e99",
                "10.1e+99",
                "10.1e-99",
                "1234535616542563465214365124365124361254315624.666E231123"
        };

        super.testTokens(tests, FLOAT);
    }

    @Test
    public void singleLineStringTest() {
        String[] tests = {
                "\"fubar\"",
                "\"a'\\\"'c\"",
                "\"a'\\X'c\"",
                "\"a\nb\""
        };

        super.testTokens(tests, SINGLE_LINE_STRING);
    }

    @Test
    public void multiLineStringTest() {
        String[] tests = {
                "\"\"\" ... \n \" \n \" \n  \"\"\"",
                "\"\"\"x\"\"\"",
                "\"\"\"\"\"\""
        };

        super.testTokens(tests, MULTI_LINE_STRING);
    }

    @Test
    public void variableTest() {
        String[] tests = {
                "snake_case",
                "sNAKE_CASE",
                "_SNAKE_CASE",
                "q42",
                "_げごさざ"
        };

        super.testTokens(tests, VARIABLE);
    }
}