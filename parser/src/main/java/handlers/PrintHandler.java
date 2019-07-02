package handlers;

import ast.ASTExpression;
import ast.ASTNode;
import ast.PrintStatement;
import handlers.rules.PrintRule;
import tokens.Token;

import java.util.List;

public class PrintHandler extends Handler<ASTNode>{

    private final ExpressionHandler expressionHandler;

    public PrintHandler(){
        super(new PrintRule());
        this.expressionHandler = new ExpressionHandler();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        ASTExpression printable = expressionHandler.handle(statement.subList(2,statement.size()-1));
        return new PrintStatement(printable);
    }
}
