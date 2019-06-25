package handlers.rules;

import tokens.Token;

import java.util.List;

public class DeclarationRule implements Rule {

    @Override
    public boolean validate(List<Token> statement) {
        return false;
    }
}
