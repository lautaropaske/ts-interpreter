package handlers.rules;

import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;

import java.util.List;
import java.util.stream.Collectors;

public class PrintRule implements Rule {
    @Override
    public void validate(List<Token> statement) {
        matchingParens(statement);
        List<Token> invalidCharacters = statement.stream()
                                                     .filter( token ->  {
                                                         TokenType tokenType = token.getType();

                                                         return  tokenType == TokenType.NUM_LITERAL || tokenType == TokenType.STR_LITERAL || tokenType == TokenType.IDENTIFIER  ||
                                                                 tokenType == TokenType.SUM  || tokenType == TokenType.MINUS  || tokenType == TokenType.DIVIDE  || tokenType == TokenType.MULTIPLY  ||
                                                                 tokenType == TokenType.L_PAREN || tokenType == TokenType.R_PAREN;
                                                     })
                                                     .collect(Collectors.toList());
        if(!invalidCharacters.isEmpty()) throw new ParserException("Invalid statement", invalidCharacters.get(0).getCoordinates()[0], statement.get(0).getCoordinates()[1]);

    }
}
