package handlers.rules;

import ast.Type;
import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;

import java.util.Arrays;
import java.util.List;

public class DeclarationRule implements Rule {

    @Override
    public void validate(List<Token> statement) {
        boolean t1 = statement.get(1).getType() == TokenType.IDENTIFIER;
        boolean t2 = statement.get(2).getType() == TokenType.COLON;
        boolean t3 = statement.get(3).getType() == TokenType.IDENTIFIER && Type.isValid(statement.get(3).getValue());
        boolean t4 = (statement.get(4).getType() == TokenType.SEMICOLON || statement.get(4).getType() == TokenType.EQUALS);
        List<Boolean> tokensValidity = Arrays.asList(t1, t2, t3, t4);

        for (int i = 1; i < tokensValidity.size()+1; i++) { // Start from 1 as statement is scanned since index 1
            if(!tokensValidity.get(i-1)) throw new ParserException("Invalid statement", statement.get(i).getCoordinates()[0], statement.get(i).getCoordinates()[1]);
        }
    }
}
