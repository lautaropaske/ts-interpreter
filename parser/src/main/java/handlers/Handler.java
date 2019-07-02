package handlers;

import handlers.rules.Rule;
import tokens.Token;

import java.util.List;

public abstract class Handler<T> {
    Rule rule;

    public T handle(List<Token> statement){
        return parseNode(statement);
    }

    protected abstract T parseNode(List<Token> statement);
}
