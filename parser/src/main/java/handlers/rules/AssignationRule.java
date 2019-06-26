package handlers.rules;

import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;

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
                if(type == TokenType.NUM_LITERAL || type == TokenType.STR_LITERAL || type == TokenType.IDENTIFIER) { mustBeAValue = false; continue; }
            } else { // If preceded by a value, then it MUST be an operator or a semicolon
                if(type == TokenType.SUM || type == TokenType.DIVIDE || type == TokenType.MULTIPLY || type == TokenType.MINUS || type == TokenType.SEMICOLON) { mustBeAValue = true; continue; }
            }
            throw new ParserException("Invalid statement", statement.get(i).getCoordinates()[0], statement.get(i).getCoordinates()[1]);
        }
    }
}
