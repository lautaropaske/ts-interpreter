package handlers;

import ast.ASTNode;
import handlers.rules.DeclarationRule;
import tokens.Token;

import java.util.List;

public class DeclarationHandler extends Handler{

    public DeclarationHandler(){
        this.rule = new DeclarationRule();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        return null;
    }
}
