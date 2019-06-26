package ast;

import exceptions.ParserException;
import handlers.*;
import tokens.Token;
import tokens.TokenType;

import java.util.List;

public class ASTNodeFactory {
    private Handler declarationHandler;
    private Handler assignationHandler;
    private Handler declarationAssignationHandler;
    private Handler printHandler;

    public ASTNodeFactory(){
        this.declarationHandler = new DeclarationHandler();
        this.assignationHandler = new AssignationHandler();
        this.declarationAssignationHandler = new DeclarationAssignationHandler();
        this.printHandler = new PrintHandler();
    }

    public ASTNode getNode(List<Token> statement) {
        switch (statement.get(0).getType()) {
            case LET:
                if(statement.get(3).getType() == TokenType.SEMICOLON) return declarationHandler.handle(statement); // If 4th token is not an 'equals' but a semicolon, means we're only in a declaration
                return declarationAssignationHandler.handle(statement);
            case IDENTIFIER:
                return assignationHandler.handle(statement);
            case PRINT:
                return printHandler.handle(statement);
            default:
                throw new ParserException("Invalid statement", statement.get(0).getCoordinates()[0] , statement.get(0).getCoordinates()[1]);
        }
    }
}
