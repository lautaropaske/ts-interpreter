package handlers.rules;

import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;

import java.util.Arrays;
import java.util.List;

public class AssignationRule implements Rule {
    // TODO Not supporting parens in assignations

    @Override
    public void validate(List<Token> statement) {
        if(statement.get(1).getType() != TokenType.EQUALS) throw new ParserException("Invalid statement", statement.get(1).getCoordinates()[0], statement.get(1).getCoordinates()[1]);

        matchingParens(statement.subList(2, statement.size()));

        // An expression is something between symbols: after an '=' or between ()
        boolean mustBeAValue = true; // Initialize scan being preceded by an equals
        for (int i = 2; i < statement.size(); i++) {
            TokenType type = statement.get(i).getType();
            if(mustBeAValue) {
                if(Arrays.asList(TokenType.NUM_LITERAL, TokenType.STR_LITERAL, TokenType.IDENTIFIER).contains(type)) { mustBeAValue = false; continue; }
            } else { // If preceded by a value, then it MUST be an operator or a semicolon
                if(Arrays.asList(TokenType.SUM, TokenType.DIVIDE, TokenType.MULTIPLY, TokenType.MINUS, TokenType.SEMICOLON).contains(type)) { mustBeAValue = true; continue; }
            }
            throw new ParserException("Invalid statement", statement.get(i).getCoordinates()[0], statement.get(i).getCoordinates()[1]);
        }
    }
}
