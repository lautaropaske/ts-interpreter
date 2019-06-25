package ast;

import exceptions.ParserException;
import handlers.AssignationHandler;
import handlers.DeclarationAssignationHandler;
import handlers.DeclarationHandler;
import handlers.PrintHandler;
import tokens.Token;
import tokens.TokenType;

import java.util.List;

public class ASTNodeFactory {
    DeclarationHandler declarationHandler;
    AssignationHandler assignationHandler;
    DeclarationAssignationHandler declarationAssignationHandler;
    PrintHandler printHandler;


    public ASTNode getNode(List<Token> statement) {
        switch (statement.get(0).getType()) {
            case LET:
                if(statement.get(3).getType() == TokenType.SEMICOLON) return declarationHandler.handle(statement);
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
