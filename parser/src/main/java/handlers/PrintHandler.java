package handlers;

import ast.ASTNode;
import ast.PrintStatement;
import handlers.rules.PrintRule;
import tokens.Token;

import java.util.List;

public class PrintHandler extends Handler{

    private final ExpressionHandler expressionHandler;

    public PrintHandler(){
        this.rule = new PrintRule();
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        ASTNode printable = expressionHandler.handle(statement);
        return new PrintStatement(printable);
    }
}
