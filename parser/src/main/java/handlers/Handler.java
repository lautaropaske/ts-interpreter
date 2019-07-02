package handlers;

import handlers.rules.Rule;
import tokens.Token;

import java.util.List;

public abstract class Handler<T> {
    private Rule rule;

    Handler(Rule rule){
        this.rule = rule;
    }

    public T handle(List<Token> statement){
        if(rule != null) rule.validate(statement);
        return parseNode(statement);
    }

    protected abstract T parseNode(List<Token> statement);
}
