package handlers.rules;

import tokens.Token;
import java.util.List;

public class DeclarationRule implements Rule {

    @Override
    public void validate(List<Token> statement) {
        isDeclaration(statement, false);
    }
}
