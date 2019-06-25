package handlers;

import ast.ASTNode;
import handlers.rules.AssignationRule;
import tokens.Token;

import java.util.List;

public class AssignationHandler extends Handler{

    public AssignationHandler(){
        this.rule = new AssignationRule();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        return null;
    }
}
