package handlers;

import ast.ASTExpression;
import ast.ASTNode;
import ast.AssignationStatement;
import ast.Identifier;
import handlers.rules.AssignationRule;
import tokens.Token;

import java.util.List;

public class AssignationHandler extends Handler<ASTNode> {

    private ExpressionHandler expressionHandler;

    public AssignationHandler() {
        super(new AssignationRule());
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        Identifier identifier = new Identifier(statement.get(0).getValue());
        ASTExpression value = expressionHandler.handle(statement.subList(2,statement.size()));
        return new AssignationStatement(identifier, value);
    }
}
