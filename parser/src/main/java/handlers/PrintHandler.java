package handlers;

import ast.ASTNode;
import handlers.rules.PrintRule;
import tokens.Token;

import java.util.List;

public class PrintHandler extends Handler{

    public PrintHandler(){
        this.rule = new PrintRule();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        return null;
    }
}
