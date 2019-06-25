package parser;

import ast.ASTNode;
import tokens.Token;

import java.util.List;

public interface Parser {
    ASTNode parse(List<Token> tokens);
}
