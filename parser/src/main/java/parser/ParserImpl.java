package parser;

import ast.ASTNode;
import ast.ASTNodeFactory;
import ast.Program;
import tokens.Token;
import tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser {
    private ASTNodeFactory factory = new ASTNodeFactory();

    @Override
    public ASTNode parse(List<Token> tokens) {
        Program program = new Program();
        List<List<Token>> statements = formStatements(tokens);

        for(List<Token> statement : statements){
            program.addStatement(factory.getNode(statement));
        }

        return program;
    }

    private List<List<Token>> formStatements(List<Token> tokens) {
        List<List<Token>> statements = new ArrayList<>();
        List<Token> acc = new ArrayList<>();

        for(Token t : tokens){
            if(t.getType() == TokenType.SPACE) continue;
            if(t.getType() == TokenType.SEMICOLON || t.getType() == TokenType.NEW_LINE){
                if(!acc.isEmpty()){
                    if (t.getType() == TokenType.SEMICOLON) acc.add(t);
                    statements.add(acc);
                    acc = new ArrayList<>();
                }
            } else {
                acc.add(t);
            }
        }

        return statements;
    }
}
