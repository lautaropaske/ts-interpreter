package handlers;

import ast.ASTNode;
import handlers.rules.Rule;
import tokens.Token;

import java.util.List;

public abstract class Handler {
    Rule rule;

    public ASTNode handle(List<Token> statement){
        return parseNode(statement);
    }

    protected abstract ASTNode parseNode(List<Token> statement);
}
