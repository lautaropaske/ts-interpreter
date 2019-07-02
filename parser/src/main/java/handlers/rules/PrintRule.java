package handlers.rules;

import tokens.Token;

import java.util.List;

public class PrintRule implements Rule {
    @Override
    public void validate(List<Token> statement) {
        matchingParens(statement);
    }
}
