package handlers.rules;

import exceptions.ParserException;
import tokens.Token;
import java.util.List;

public interface Rule {
    boolean validate(List<Token> statement) throws ParserException;
}
