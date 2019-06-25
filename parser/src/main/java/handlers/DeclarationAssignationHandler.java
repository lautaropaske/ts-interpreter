package handlers;


import ast.ASTNode;
import handlers.rules.DeclarationAndAssignationRule;
import tokens.Token;
import java.util.List;

public class DeclarationAssignationHandler extends Handler {

    public DeclarationAssignationHandler(){
        this.rule = new DeclarationAndAssignationRule();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        return null;
    }
}
