package handlers;

import ast.ASTNode;
import ast.DeclarationStatement;
import ast.Identifier;
import ast.Type;
import handlers.rules.DeclarationRule;
import tokens.Token;
import java.util.List;

public class DeclarationHandler extends Handler{

    public DeclarationHandler(){
        this.rule = new DeclarationRule();
    }

    @Override
    protected ASTNode parseNode(List<Token> statement) {
        return new DeclarationStatement(new Identifier(statement.get(1).getValue()), Type.from(statement.get(3)));
    }
}
