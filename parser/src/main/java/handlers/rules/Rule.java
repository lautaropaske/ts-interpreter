package handlers.rules;

import exceptions.ParserException;
import tokens.Token;
import tokens.TokenType;

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
}
