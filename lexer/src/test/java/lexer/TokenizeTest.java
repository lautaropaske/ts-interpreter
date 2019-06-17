package lexer;

import org.junit.jupiter.api.Test;
import tokens.Token;
import tokens.TokenType;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class TokenizeTest {

    private Lexer lexer = new LexerImpl();

    @Test
    public void tokenizeAnAssignation() {
        String line = "let foo = 5;";
        List<Token> tokens = this.lexer.tokenize(line);

        List<TokenType> expectedTokens = Arrays.asList(
                TokenType.KEYWORD,
                TokenType.DELIMITER,
                TokenType.IDENTIFIER,
                TokenType.DELIMITER,
                TokenType.SYMBOL,
                TokenType.DELIMITER,
                TokenType.NUM_LITERAL,
                TokenType.SYMBOL
        );

        assertTokens(expectedTokens, tokens);
    }

    @Test
    public void tokenizeAResignation() {
        String line = "let foo = 5;\nfoo = 10;";
        List<Token> tokens = this.lexer.tokenize(line);

        List<TokenType> expectedTokens = Arrays.asList(
                TokenType.KEYWORD,      // 1st line
                TokenType.DELIMITER,
                TokenType.IDENTIFIER,
                TokenType.DELIMITER,
                TokenType.SYMBOL,
                TokenType.DELIMITER,
                TokenType.NUM_LITERAL,
                TokenType.SYMBOL,
                TokenType.DELIMITER,    // 2nd line
                TokenType.IDENTIFIER,
                TokenType.DELIMITER,
                TokenType.SYMBOL,
                TokenType.DELIMITER,
                TokenType.NUM_LITERAL,
                TokenType.SYMBOL

        );

        assertTokens(expectedTokens, tokens);
    }

    @Test
    public void tokenizeTypedAssignation() {
        String line = "let foo : string = \'test\';";
        List<Token> tokens = this.lexer.tokenize(line);

        List<TokenType> expectedTokens = Arrays.asList(
                TokenType.KEYWORD,      // let
                TokenType.DELIMITER,    //
                TokenType.IDENTIFIER,   // foo
                TokenType.DELIMITER,    //
                TokenType.SYMBOL,       // :
                TokenType.DELIMITER,    //
                TokenType.IDENTIFIER,   // string
                TokenType.DELIMITER,    //
                TokenType.SYMBOL,       // =
                TokenType.DELIMITER,    //
                TokenType.STR_LITERAL,  // 'test'
                TokenType.SYMBOL        // ;

        );

        assertTokens(expectedTokens, tokens);
    }

    @Test
    public void checkTokensCoordinates() {
        String line = "let foo = \'test\';\n" +
                      "let bar;";
        List<Token> tokens = this.lexer.tokenize(line);

        List<int[]> expectedCoordinates = Arrays.asList(
                new int[]{1,1}, // let
                new int[]{1,4}, //
                new int[]{1,5}, // foo
                new int[]{1,8}, //
                new int[]{1,9}, // =
                new int[]{1,10},//
                new int[]{1,11},// 'test'
                new int[]{1,17},// ;
                new int[]{1,18},// \n
                new int[]{2,1}, // let
                new int[]{2,4}, //
                new int[]{2,5}, // bar
                new int[]{2,8}  // ;

        );

        assertCoordinates(expectedCoordinates, tokens);
    }

    private void assertTokens(List<TokenType> expectedTokens, List<Token> tokens) {
        for(int i = 0; i < expectedTokens.size(); i++) assertEquals(expectedTokens.get(i), tokens.get(i).getType());
    }

    private void assertCoordinates(List<int[]> expectedCoordinates, List<Token> tokens) {
        for(int i = 0; i < expectedCoordinates.size(); i++) {
            assertEquals(expectedCoordinates.get(i)[0], tokens.get(i).getCoordinates()[0]);
            assertEquals(expectedCoordinates.get(i)[1], tokens.get(i).getCoordinates()[1]);
        }
    }

}
