package nl.bigo.elixirp;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import java.util.List;
import static org.junit.Assert.*;

public abstract class BaseTest {

    protected void testTokens(String[] inputs, int expectedTokenType) {
        for (var input : inputs) {
            this.testSingleToken(input, expectedTokenType);
        }
    }

    protected void testSingleToken(String input, int expectedTokenType) {
        var token = this.SingleToken(input);
        assertEquals(expectedTokenType, token.getType());
    }

    protected List<Token> CreateTokens(String input) {
        var charStream = CharStreams.fromString(input);
        var tokenStream = new CommonTokenStream(new ElixirLexer(charStream));

        tokenStream.fill();

        var tokens = tokenStream.getTokens();

        return tokens.subList(0, tokens.size() - 1);
    }

    protected Token SingleToken(String input) {
        var tokens = CreateTokens(input);

        if (tokens.size() != 1) {
            throw new RuntimeException(String.format("'%s' produced %d tokens, expected 1", input, tokens.size()));
        }

        return tokens.get(0);
    }

    protected String alternativeClassName(String alternative) {
        return "%s%sContext".formatted(
                Character.toUpperCase(alternative.charAt(0)),
                alternative.substring(1));
    }
}
