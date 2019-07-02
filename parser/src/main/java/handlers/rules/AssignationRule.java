package handlers.rules;

import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;

import java.util.List;

public class AssignationRule implements Rule {
    // TODO Not supporting parens in assignations

    @Override
    public void validate(List<Token> statement) {
        if(statement.get(0).getType() == TokenType.IDENTIFIER) throw new ParserException("Invalid statement", statement.get(0).getCoordinates()[0], statement.get(0).getCoordinates()[1]);
        if(statement.get(1).getType() != TokenType.EQUALS) throw new ParserException("Invalid statement", statement.get(1).getCoordinates()[0], statement.get(1).getCoordinates()[1]);

        matchingParens(statement.subList(2, statement.size()));
        isExpression(statement.subList(2, statement.size()));
    }
}
