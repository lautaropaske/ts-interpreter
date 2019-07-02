package handlers;

import ast.ASTNode;
import ast.DeclarationStatement;
import ast.Identifier;
import ast.Type;
import exceptions.ParserException;
import handlers.rules.DeclarationRule;
import tokens.Token;
import java.util.List;

public class DeclarationHandler extends Handler<ASTNode>{

    public DeclarationHandler(){
        this.rule = new DeclarationRule();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        if(statement.size() < 4) throw new ParserException("Declaration is incomplete", statement.get(0).getCoordinates()[0], statement.get(0).getCoordinates()[1]);
        return new DeclarationStatement(new Identifier(statement.get(1).getValue()), Type.from(statement.get(3)));
    }
}
