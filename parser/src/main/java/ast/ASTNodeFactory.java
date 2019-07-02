package ast;

import exceptions.ParserException;
import handlers.*;
import tokens.Token;
import tokens.TokenType;

import java.util.List;

public class ASTNodeFactory {
    private Handler<ASTNode> declarationHandler;
    private Handler<ASTNode> assignationHandler;
    private Handler<ASTNode> declarationAssignationHandler;
    private Handler<ASTNode> printHandler;

    public ASTNodeFactory(){
        this.declarationHandler = new DeclarationHandler();
        this.assignationHandler = new AssignationHandler();
        this.declarationAssignationHandler = new DeclarationAssignationHandler();
        this.printHandler = new PrintHandler();
    }

    public ASTNode getNode(List<Token> statement) {
        Token firstToken = statement.get(0);

        switch (firstToken.getType()) {
            case LET:
                if(statement.size() <= 5) {
                    return declarationHandler.handle(statement); // If 5th token is not an 'equals', means we're only in a declaration
                } else if (statement.get(4).getType() == TokenType.EQUALS){
                    return declarationAssignationHandler.handle(statement);
                }
                break;
            case IDENTIFIER:
                return assignationHandler.handle(statement);
            case PRINT:
                return printHandler.handle(statement);
            default:
                throw new ParserException("Invalid statement", firstToken.getCoordinates()[0] , firstToken.getCoordinates()[1]);
        }
        throw new ParserException("Invalid statement", firstToken.getCoordinates()[0] , firstToken.getCoordinates()[1]);
    }
}
