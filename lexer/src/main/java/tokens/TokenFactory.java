package tokens;

import exceptions.LexerException;
import utils.Definitions;

public class TokenFactory {

    public Token getTokenFor(String str, int[] coordinates){
        switch (str){
            case "let": return new TokenImpl(TokenType.LET, coordinates, str);
            case "print": return new TokenImpl(TokenType.PRINT, coordinates, str);
            case ";": return new TokenImpl(TokenType.SEMICOLON, coordinates, str);
            case ":": return new TokenImpl(TokenType.COLON, coordinates, str);
            case "=": return new TokenImpl(TokenType.EQUALS, coordinates, str);
            case " ": return new TokenImpl(TokenType.SPACE, coordinates, str);
            case "\n": return new TokenImpl(TokenType.NEW_LINE, coordinates, str);
            case "(": return new TokenImpl(TokenType.L_PAREN, coordinates, str);
            case ")": return new TokenImpl(TokenType.R_PAREN, coordinates, str);
            case "/": return new TokenImpl(TokenType.DIVIDE, coordinates, str);
            case "-": return new TokenImpl(TokenType.MINUS, coordinates, str);
            case "*": return new TokenImpl(TokenType.MULTIPLY, coordinates, str);
            case "+": return new TokenImpl(TokenType.SUM, coordinates, str);
            default: return identifyToken(str, coordinates);

        }
    }

    private Token identifyToken(String str, int[] coordinates) {
        if(str.matches(Definitions.STR_LITERAL_REGEX)) return new TokenImpl(TokenType.STR_LITERAL, coordinates, str);
        if(str.matches(Definitions.NUM_LITERAL_REGEX)) return new TokenImpl(TokenType.NUM_LITERAL, coordinates, str);
        if(str.matches(Definitions.IDENTIFIER_REGEX)) return new TokenImpl(TokenType.IDENTIFIER, coordinates, str);
        throw new LexerException(str + ": unknown or invalid token type", coordinates[0], coordinates[1]);
    }
}
