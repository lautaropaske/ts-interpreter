package handlers;

import ast.ASTNode;
import ast.AssignationStatement;
import ast.Identifier;
import handlers.rules.AssignationRule;
import tokens.Token;

import java.util.List;

public class AssignationHandler extends Handler {

    private ExpressionHandler expressionHandler;

    public AssignationHandler() {
        this.rule = new AssignationRule();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        Identifier identifier = new Identifier(statement.get(0).getValue());
        ASTNode value = expressionHandler.handle(statement);
        return new AssignationStatement(identifier, value);
    }
}
