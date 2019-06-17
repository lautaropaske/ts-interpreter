package tokens;

import utils.Utils;

public class TokenFactory {

    public Token getTokenFor(String str, int[] coordinates){
        if (Utils.KEYWORD_LIST.contains(str)) return new TokenImpl(TokenType.KEYWORD, coordinates, null);
        if (Utils.SYMBOL_LIST.contains(str)) return new TokenImpl(TokenType.SYMBOL, coordinates, null);
        if (Utils.DELIMITER_LIST.contains(str)) return new TokenImpl(TokenType.DELIMITER, coordinates, null);
        return identifyToken(str, coordinates);
    }

    private Token identifyToken(String str, int[] coordinates) {
        if(str.matches(Utils.STR_LITERAL_REGEX)) return new TokenImpl(TokenType.STR_LITERAL, coordinates, str);
        if(str.matches(Utils.NUM_LITERAL_REGEX)) return new TokenImpl(TokenType.NUM_LITERAL, coordinates, str);
        if(str.matches(Utils.IDENTIFIER_REGEX)) return new TokenImpl(TokenType.IDENTIFIER, coordinates, str);
        return new TokenImpl(TokenType.UNKNOWN, coordinates, str);
    }
}
