package handlers.rules;

import ast.Type;
import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;

import java.util.Arrays;
import java.util.List;

public interface Rule {
    void validate(List<Token> statement) throws ParserException;
    default void matchingParens(List<Token> statement) {
        int lParenCount = 0;
        int rParenCount = 0;

        int i;
        for (i = 1; i < statement.size(); i++) {
            TokenType type = statement.get(i).getType();

            // Instead of just counting '(' and ')', you also have to check that there's valid semantics
            if(type == TokenType.L_PAREN) { lParenCount++; continue; }       // Found an '(', augment counter
            if(type == TokenType.R_PAREN) {                                  // If found an ')'
                if(rParenCount < lParenCount) { rParenCount++; continue; }   // check that AT LEAST there's one ')' to match it
                throw new ParserException("Invalid statement", statement.get(i).getCoordinates()[0], statement.get(i).getCoordinates()[1]); // else throw parsing exception
            }
        }

        // If loop ends with mismatched parens (only possible when the last R_PAREN is missing)
        // Token is i-1 as the loop ends the statement at statement.size()
        if(lParenCount != rParenCount) throw new ParserException("Invalid statement", statement.get(i-1).getCoordinates()[0], statement.get(i-1).getCoordinates()[1]);

    }
    default void isExpression(List<Token> statement){
        // An expression is something between symbols: after an '=' or between ()

        boolean mustBeAValue = true; // Initialize scan being preceded by an equals
        for (Token token : statement) {
            TokenType type = token.getType();
            if (mustBeAValue) {
                if (Arrays.asList(TokenType.NUM_LITERAL, TokenType.STR_LITERAL, TokenType.IDENTIFIER).contains(type)) {
                    mustBeAValue = false;
                    continue;
                }
            } else { // If preceded by a value, then it MUST be an operator or a semicolon
                if (Arrays.asList(TokenType.SUM, TokenType.DIVIDE, TokenType.MULTIPLY, TokenType.MINUS, TokenType.SEMICOLON).contains(type)) {
                    mustBeAValue = true;
                    continue;
                }
            }
            throw new ParserException("Invalid statement", token.getCoordinates()[0], token.getCoordinates()[1]);
        }
    }

    default void isDeclaration(List<Token> statement, boolean fromDeclarationAssignation){
        if(statement.size() < 4) throw new ParserException("Invalid statement", statement.get(0).getCoordinates()[0], statement.get(0).getCoordinates()[1]);

        boolean t1 = statement.get(1).getType() == TokenType.IDENTIFIER;
        boolean t2 = statement.get(2).getType() == TokenType.COLON;
        boolean t3 = statement.get(3).getType() == TokenType.IDENTIFIER && Type.isValid(statement.get(3).getValue());
        boolean t4 = statement.get(4).getType() == TokenType.SEMICOLON || (statement.get(4).getType() == TokenType.EQUALS && fromDeclarationAssignation);
        List<Boolean> tokensValidity = Arrays.asList(t1, t2, t3, t4);

        for (int i = 1; i < tokensValidity.size()+1; i++) { // Start from 1 as statement is scanned since index 1
            if(!tokensValidity.get(i-1)) throw new ParserException("Invalid statement", statement.get(i).getCoordinates()[0], statement.get(i).getCoordinates()[1]);
        }
    }
}
