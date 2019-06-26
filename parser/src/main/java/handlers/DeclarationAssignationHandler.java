package handlers;


import ast.ASTNode;
import ast.DeclarationAssignationStatement;
import ast.Identifier;
import ast.Type;
import handlers.rules.DeclarationAndAssignationRule;
import tokens.Token;
import java.util.List;

public class DeclarationAssignationHandler extends Handler {

    private ExpressionHandler expressionHandler;

    public DeclarationAssignationHandler(){
        this.rule = new DeclarationAndAssignationRule();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        return new DeclarationAssignationStatement(new Identifier(statement.get(1).getValue()), Type.from(statement.get(3)), expressionHandler.handle(statement));
    }
}
